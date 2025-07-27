# ZXF Java JSON-RPC

一个基于Spring WebFlux和jsonrpc4j的JSON-RPC服务示例项目。

## 功能特点

- 基于Spring WebFlux的响应式编程模型
- 集成jsonrpc4j实现JSON-RPC 2.0协议
- 支持请求跟踪（Tracing）
- 提供简单的计算器服务示例

## 项目结构

```
src/main/java/zxf/restrpc/
├── RestRpcApplication.java              # 应用程序入口类
├── app/                                 # 应用程序代码
│   ├── controller/                      # REST控制器
│   │   └── HealthController.java        # 健康检查控制器
│   └── service/                         # 服务接口和实现
│       ├── CalculatorService.java       # 计算器服务接口
│       └── impl/
│           └── CalculatorServiceImpl.java # 计算器服务实现
├── config/                              # 配置类
│   └── JsonRpcConfig.java               # JSON-RPC配置
└── support/                             # 支持类
    └── trace/                           # 请求跟踪相关
        ├── TraceContext.java            # 跟踪上下文
        └── TraceFilter.java             # 跟踪过滤器
```

## 快速开始

### 构建项目

```bash
./mvnw clean package
```

### 运行项目

```bash
java -jar target/zxf-java-json-rpc-0.0.1-SNAPSHOT.jar
```

或者使用Maven运行：

```bash
./mvnw spring-boot:run
```

### 测试健康检查

```bash
curl http://localhost:8080/health
```

### 测试JSON-RPC服务

使用以下命令测试计算器服务的加法功能：

```bash
curl -X POST -H "Content-Type: application/json" -d '{"jsonrpc":"2.0","method":"add","params":{"a":10,"b":20},"id":1}' http://localhost:8080/rpc/calculator
```

预期响应：

```json
{"jsonrpc":"2.0","result":30,"id":1}
```

## JSON-RPC方法

计算器服务提供以下方法：

- `add(a, b)` - 返回两数之和
- `subtract(a, b)` - 返回两数之差
- `multiply(a, b)` - 返回两数之积
- `divide(a, b)` - 返回两数之商（注意：除数不能为0）

## 依赖

- Spring Boot 2.7.x
- Spring WebFlux
- jsonrpc4j
- Lombok
- SLF4J & Logback

## 许可证

MIT
