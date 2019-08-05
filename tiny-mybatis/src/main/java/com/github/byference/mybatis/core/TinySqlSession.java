package com.github.byference.mybatis.core;

/**
 * TinySqlSession
 *
 * @author byference
 * @since 2019-08-03
 */
public class TinySqlSession {

    private TinyConfiguration configuration;


    private TinyExecutor executor;

    public TinySqlSession(TinyConfiguration configuration, TinyExecutor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }
}
