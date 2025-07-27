package zxf.jsonrpc.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.jsonrpc4j.JsonRpcServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import zxf.jsonrpc.app.service.CalculatorService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * JSON-RPC配置类
 */
@Slf4j
@Configuration
public class JsonRpcConfig {

    /**
     * 创建JSON-RPC服务器
     * @param calculatorService 计算器服务实现
     * @param objectMapper JSON对象映射器
     * @return JsonRpcServer实例
     */
    @Bean
    public JsonRpcServer calculatorJsonRpcServer(CalculatorService calculatorService, ObjectMapper objectMapper) {
        log.info("Configuring JsonRpcServer for CalculatorService");
        return new JsonRpcServer(objectMapper, calculatorService, CalculatorService.class);
    }

    /**
     * 配置路由函数，处理JSON-RPC请求
     * @param jsonRpcServer JSON-RPC服务器
     * @return RouterFunction实例
     */
    @Bean
    public RouterFunction<ServerResponse> calculatorRouterFunction(JsonRpcServer jsonRpcServer) {
        return RouterFunctions.route()
                .POST("/rpc/calculator", request -> handleJsonRpcRequest(request, jsonRpcServer))
                .build();
    }

    /**
     * 处理JSON-RPC请求
     * @param request 服务器请求
     * @param jsonRpcServer JSON-RPC服务器
     * @return 服务器响应
     */
    private Mono<ServerResponse> handleJsonRpcRequest(ServerRequest request, JsonRpcServer jsonRpcServer) {
        return request.bodyToMono(byte[].class)
                .flatMap(requestBody -> {
                    try {
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        jsonRpcServer.handleRequest(new ByteArrayInputStream(requestBody), outputStream);
                        byte[] responseBody = outputStream.toByteArray();
                        return ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(responseBody);
                    } catch (IOException e) {
                        log.error("Error handling JSON-RPC request", e);
                        return ServerResponse.badRequest().build();
                    }
                });
    }
}
