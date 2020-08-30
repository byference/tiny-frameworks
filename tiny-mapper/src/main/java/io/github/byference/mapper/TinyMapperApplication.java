package io.github.byference.mapper;

import io.github.byference.mapper.core.TinyMapperFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TinyMapperApplication
 *
 * @author byference
 * @since 2020-08-22
 */
@MapperScan(basePackages = "io.github.byference.mapper.mapper", factoryBean = TinyMapperFactoryBean.class)
@SpringBootApplication
public class TinyMapperApplication {

    public static void main(String[] args) {
        SpringApplication.run(TinyMapperApplication.class, args);
    }

}
