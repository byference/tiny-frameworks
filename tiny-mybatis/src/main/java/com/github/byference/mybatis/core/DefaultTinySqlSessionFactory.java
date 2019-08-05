package com.github.byference.mybatis.core;

/**
 * DefaultTinySqlSessionFactory
 *
 * @author byference
 * @since 2019-08-03
 */
public class DefaultTinySqlSessionFactory implements TinySqlSessionFactory {


    private final TinyConfiguration configuration;


    public DefaultTinySqlSessionFactory(TinyConfiguration configuration) {
        this.configuration = configuration;
    }



    @Override
    public TinySqlSession openSession() {

        TinyExecutor executor = new TinyExecutor(configuration);
        return new TinySqlSession(configuration, executor);
    }

    @Override
    public TinyConfiguration getConfiguration() {
        return configuration;
    }
}
