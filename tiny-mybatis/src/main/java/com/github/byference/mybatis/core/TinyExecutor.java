package com.github.byference.mybatis.core;

import com.alibaba.fastjson.JSONObject;
import com.github.byference.mybatis.util.RegexUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

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


    public List<Object> query(MapperStatement mapperStatement, Object[] args) {

        List<Object> list = new ArrayList<>();

        String sql = mapperStatement.getSql();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();

            JSONObject jsonObject = new JSONObject();
            Class<?> clazz = Class.forName(mapperStatement.getResultType());

            while (resultSet.next()) {

                for (int i = 1; i < metaData.getColumnCount() + 1; i++) {

                    String columnName = RegexUtil.underline2Hump(metaData.getColumnName(i));
                    Object object = resultSet.getObject(i);
                    jsonObject.put(columnName, object);
                }

                Object object = jsonObject.toJavaObject(clazz);
                list.add(object);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


}
