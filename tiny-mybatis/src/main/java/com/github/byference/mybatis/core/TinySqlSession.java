package com.github.byference.mybatis.core;

import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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


    @SuppressWarnings("unchecked")
    public <T> T getMapper(Class<T> clazz) {

        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, (proxy, method, args) -> {

            Object result;
            String statementKey = clazz.getName() + "." + method.getName();
            Class<?> returnType = method.getReturnType();

            // execute sql
            if (Collection.class.isAssignableFrom(returnType)) {
                // collection
                result = selectList(statementKey, args);
            } else if (Map.class.isAssignableFrom(returnType)) {
                // map
                result = selectMap(statementKey, args);
            } else {
                // Object
                result = selectOne(statementKey, args);
            }
            return result;
        });
    }



    private Map<String, Object> selectMap(String statementKey, Object[] args) {


        return null;
    }


    public List<Object> selectList(String statementKey, Object[] args) {

        MapperStatement mapperStatement = configuration.getMapperStatementMap().get(statementKey);
        // execute sql
        return executor.query(mapperStatement, args);
    }


    public Object selectOne(String statementKey, Object[] args) {

        MapperStatement mapperStatement = configuration.getMapperStatementMap().get(statementKey);
        List<Object> results = executor.query(mapperStatement, args);

        if (results.size() == 1) {
            return results.get(0);
        } else if (results.size() > 1) {
            throw new IllegalArgumentException("Expected one result (or null) to be returned by selectOne(), but found: " + results.size());
        } else {
            return null;
        }
    }


}
