package zxf.jsonrpc.app.service;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;

/**
 * 计算器服务接口，提供基本的数学运算功能
 */
@JsonRpcService("calculator")
public interface CalculatorService {

    /**
     * 加法运算
     * @param a 第一个操作数
     * @param b 第二个操作数
     * @return 两数之和
     */
    int add(@JsonRpcParam("a") int a, @JsonRpcParam("b") int b);

    /**
     * 减法运算
     * @param a 第一个操作数
     * @param b 第二个操作数
     * @return 两数之差
     */
    int subtract(@JsonRpcParam("a") int a, @JsonRpcParam("b") int b);

    /**
     * 乘法运算
     * @param a 第一个操作数
     * @param b 第二个操作数
     * @return 两数之积
     */
    int multiply(@JsonRpcParam("a") int a, @JsonRpcParam("b") int b);

    /**
     * 除法运算
     * @param a 第一个操作数
     * @param b 第二个操作数
     * @return 两数之商
     * @throws ArithmeticException 当除数为0时抛出
     */
    double divide(@JsonRpcParam("a") int a, @JsonRpcParam("b") int b) throws ArithmeticException;
}
