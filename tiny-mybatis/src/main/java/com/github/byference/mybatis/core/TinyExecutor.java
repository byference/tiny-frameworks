package com.github.byference.mybatis.core;

import javax.sql.DataSource;

/**
 * TinyExecutor
 *
 * @author byference
 * @since 2019-08-04
 */
public class TinyExecutor {

    private DataSource dataSource;


    public TinyExecutor(TinyConfiguration configuration) {

        this.dataSource = TinyDataSource.getInstance(configuration.getEnvironment());
    }

}
