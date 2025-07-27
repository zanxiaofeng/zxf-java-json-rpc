# 项目描述
- 这是一个基于Java实现的Rest API，用来Demo JSON-RPC在Java上的实现

# 技术规范
- 使用Maven作为构建工具，JDK使用JDK17
- 使用Springboot WebFlux 框架，使用最新的 Springbook3版本
- 通过Lombok的@Slf4j注解引入slf作为日志API
- 实际日志输出采用logback
- 项目的主package为zxf.jsonrpc
- 项目主类为JsonRpcApplication
- 项目中有关Log trace相关的功能在zxf.jsonrpc.support.trace包
- 项目中的RestControl相关的功能在zxf.jsonrpc.app.control包中
- 

# 业务需求
- 实现一个典型的JSON-RPC endpoint
- 实现一些基于JSON-RPC的经典示例
- 请使用jsonrpc4j库