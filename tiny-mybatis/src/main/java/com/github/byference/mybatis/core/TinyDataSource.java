package com.github.byference.mybatis.core;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * TinyDataSource
 *
 * @author byference
 * @since 2019-08-04
 */
public class TinyDataSource extends DataSourceAdapter {


    private TinyEnvironment environment;

    private static TinyDataSource instance;

    private HikariDataSource dataSource;


    private TinyDataSource(TinyEnvironment environment) {
        this.environment = environment;
        this.poolInit();
    }


    public static DataSource getInstance(TinyEnvironment environment) {

        if (instance == null) {
            synchronized (TinyDataSource.class) {
                if (instance == null) {
                    instance = new TinyDataSource(environment);
                }
            }
        }
        return instance;
    }


    @Override
    public Connection getConnection() throws SQLException {

        if (dataSource == null) {
            throw new IllegalStateException("failure to initialize database pool");
        }
        return dataSource.getConnection();
    }


    private void poolInit() {
        dataSource = new HikariDataSource();
        dataSource.setDriverClassName(environment.getDriver());
        dataSource.setJdbcUrl(environment.getUrl());
        dataSource.setUsername(environment.getUsername());
        dataSource.setPassword(environment.getPassword());
    }

}
