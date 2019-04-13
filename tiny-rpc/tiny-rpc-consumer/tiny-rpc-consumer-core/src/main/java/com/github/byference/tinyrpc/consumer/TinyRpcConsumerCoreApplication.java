package com.github.byference.tinyrpc.consumer;

import com.github.byference.tinyrpc.core.annotation.EnableRpcClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author byference
 * @since 2019/03/31
 */
@SpringBootApplication
@EnableRpcClient
public class TinyRpcConsumerCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(TinyRpcConsumerCoreApplication.class, args);
    }

}
