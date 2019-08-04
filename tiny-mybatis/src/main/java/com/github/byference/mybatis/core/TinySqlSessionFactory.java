package com.github.byference.mybatis.core;

/**
 * TinySqlSessionFactory
 *
 * @author byference
 * @since 2019-08-03
 */
public interface TinySqlSessionFactory {


    TinySqlSession openSession();

    TinyConfiguration getConfiguration();

}
