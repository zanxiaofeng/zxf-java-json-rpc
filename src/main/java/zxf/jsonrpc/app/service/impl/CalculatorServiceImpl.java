package zxf.jsonrpc.app.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zxf.jsonrpc.app.service.CalculatorService;
import zxf.jsonrpc.support.trace.TraceContext;

/**
 * 计算器服务实现类
 */
@Slf4j
@Service
public class CalculatorServiceImpl implements CalculatorService {

    @Override
    public int add(int a, int b) {
        log.debug("[TraceId: {}] Performing addition: {} + {}", TraceContext.getTraceId(), a, b);
        return a + b;
    }

    @Override
    public int subtract(int a, int b) {
        log.debug("[TraceId: {}] Performing subtraction: {} - {}", TraceContext.getTraceId(), a, b);
        return a - b;
    }

    @Override
    public int multiply(int a, int b) {
        log.debug("[TraceId: {}] Performing multiplication: {} * {}", TraceContext.getTraceId(), a, b);
        return a * b;
    }

    @Override
    public double divide(int a, int b) throws ArithmeticException {
        log.debug("[TraceId: {}] Performing division: {} / {}", TraceContext.getTraceId(), a, b);
        if (b == 0) {
            log.error("[TraceId: {}] Division by zero attempted", TraceContext.getTraceId());
            throw new ArithmeticException("Cannot divide by zero");
        }
        return (double) a / b;
    }
}
