package zxf.jsonrpc.app.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import zxf.jsonrpc.support.trace.TraceContext;

import java.util.HashMap;
import java.util.Map;

/**
 * 健康检查控制器
 */
@Slf4j
@RestController
public class HealthController {

    /**
     * 健康检查端点
     * @return 健康状态信息
     */
    @GetMapping("/health")
    public Mono<Map<String, Object>> health() {
        return TraceContext.getTraceIdFromContext()
                .map(traceId -> {
                    log.info("[TraceId: {}] Health check requested", traceId);
                    Map<String, Object> response = new HashMap<>();
                    response.put("status", "UP");
                    response.put("timestamp", System.currentTimeMillis());
                    response.put("traceId", traceId);
                    return response;
                });
    }
}
