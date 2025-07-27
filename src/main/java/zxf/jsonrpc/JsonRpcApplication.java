package zxf.jsonrpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;

/**
 * JSON RPC应用程序入口类
 */
@Slf4j
@SpringBootApplication
public class JsonRpcApplication {

    public static void main(String[] args) {
        log.info("Starting JSON RPC Application");
        SpringApplication.run(JsonRpcApplication.class, args);
        log.info("JSON RPC Application started successfully");
    }
}
