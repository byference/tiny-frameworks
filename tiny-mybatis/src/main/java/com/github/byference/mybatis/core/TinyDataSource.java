package com.github.byference.mybatis.core;

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


    public static DataSource getInstance(TinyEnvironment environment) {

        return null;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return super.getConnection();
    }


}
