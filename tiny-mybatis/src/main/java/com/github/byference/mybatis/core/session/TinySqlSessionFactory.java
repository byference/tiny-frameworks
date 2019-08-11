package com.github.byference.mybatis.core.session;

import com.github.byference.mybatis.core.TinyConfiguration;
import com.github.byference.mybatis.core.session.defaults.DefaultTinySqlSession;

/**
 * TinySqlSessionFactory
 *
 * @author byference
 * @since 2019-08-03
 */
public interface TinySqlSessionFactory {


    DefaultTinySqlSession openSession();

    TinyConfiguration getConfiguration();

}
