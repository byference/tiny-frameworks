package com.github.byference.mybatis.core;

import lombok.Data;

import java.util.Map;

/**
 * TinyConfiguration
 *
 * @author byference
 * @since 2019-08-03
 */
@Data
public class TinyConfiguration {


    private TinyEnvironment environment;

    private Map<String, MapperStatement> mapperStatementMap;


    public Object selectList(String statementKey, Object[] args) {


        // configuration 获取 statementKey

        // executor 执行sql


        return null;
    }




    public Object selectOne(String statementKey, Object[] args) {

        return null;
    }



}
