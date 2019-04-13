package com.github.byference.tinyrpc.provider;

import com.github.byference.tinyrpc.core.annotation.EnableRpcServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author byference
 * @since 2019/03/31
 */
@SpringBootApplication
@EnableRpcServer
public class TinyRpcProviderCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(TinyRpcProviderCoreApplication.class, args);
    }

}
