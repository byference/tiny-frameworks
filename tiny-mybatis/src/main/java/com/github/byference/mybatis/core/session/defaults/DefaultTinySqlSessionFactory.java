package com.github.byference.mybatis.core.session.defaults;

import com.github.byference.mybatis.core.TinyConfiguration;
import com.github.byference.mybatis.core.executor.TinyExecutor;
import com.github.byference.mybatis.core.session.TinySqlSessionFactory;
import com.github.byference.mybatis.core.session.defaults.DefaultTinySqlSession;

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
    public DefaultTinySqlSession openSession() {

        TinyExecutor executor = new TinyExecutor(configuration);
        return new DefaultTinySqlSession(configuration, executor);
    }


    @Override
    public TinyConfiguration getConfiguration() {
        return configuration;
    }

}
