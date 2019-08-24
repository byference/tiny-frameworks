package com.github.byference.pagination;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TinyPaginationApplication
 *
 * @author byference
 * @since 2019-08-24
 */
@MapperScan("com.github.byference.pagination.test.mapper")
@SpringBootApplication
public class TinyPaginationApplication {

    public static void main(String[] args) {
        SpringApplication.run(TinyPaginationApplication.class, args);
    }

}
