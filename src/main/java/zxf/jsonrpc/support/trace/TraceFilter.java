package zxf.jsonrpc.support.trace;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * 请求跟踪过滤器，为每个请求添加一个唯一的traceId
 */
@Slf4j
@Component
public class TraceFilter implements WebFilter {

    private static final String TRACE_ID = "traceId";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String traceId = UUID.randomUUID().toString().replace("-", "");

        log.debug("Request received with traceId: {}", traceId);

        MDC.put(TRACE_ID, traceId);
        exchange.getAttributes().put(TRACE_ID, traceId);

        return chain.filter(exchange)
                .contextWrite(context -> context.put(TRACE_ID, traceId))
                .doFinally(signalType -> MDC.remove(TRACE_ID));
    }
}
