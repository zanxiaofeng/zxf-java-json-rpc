package zxf.jsonrpc.support.trace;

import org.slf4j.MDC;
import reactor.core.publisher.Mono;

/**
 * 跟踪上下文，用于存储和获取traceId
 */
public class TraceContext {

    private static final String TRACE_ID = "traceId";

    /**
     * 获取当前的traceId
     * @return 当前的traceId，如果不存在则返回null
     */
    public static String getTraceId() {
        return MDC.get(TRACE_ID);
    }

    /**
     * 从Reactor上下文中获取traceId
     * @return 包含traceId的Mono
     */
    public static Mono<String> getTraceIdFromContext() {
        return Mono.deferContextual(ctx ->
            Mono.justOrEmpty(ctx.getOrEmpty(TRACE_ID).map(Object::toString))
        );
    }
}
