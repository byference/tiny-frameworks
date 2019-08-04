package com.github.byference.mybatis.core;


import java.io.InputStream;
import java.util.Properties;

/**
 * TinySqlSessionFactoryBuilder
 *
 * @author byference
 * @since 2019-08-03
 */
public class TinySqlSessionFactoryBuilder {


    public TinySqlSessionFactory build(InputStream inputStream) {

        Properties properties = XMLParser.getDataSourceProperties(inputStream);

        TinyConfiguration configuration = new TinyConfiguration();
        TinyEnvironment environment = new TinyEnvironment();

        environment.setDriver(properties.getProperty("driver"));
        environment.setUrl(properties.getProperty("url"));
        environment.setUsername(properties.getProperty("username"));
        environment.setPassword(properties.getProperty("password"));
        configuration.setEnvironment(environment);

        return new DefaultTinySqlSessionFactory(configuration);
    }

}
