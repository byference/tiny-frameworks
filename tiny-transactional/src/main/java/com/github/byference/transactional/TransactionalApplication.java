package com.github.byference.transactional;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Transactional {@link SpringApplication}
 *
 * @author byference
 * @since 2019-09-08
 */
@SpringBootApplication
@MapperScan("com.github.byference.transactional.mapper")
public class TransactionalApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionalApplication.class, args);
    }
}
