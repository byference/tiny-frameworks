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

            Object result = null;
            String statementKey = clazz.getName() + "." + method.getName();
            Class<?> returnType = method.getReturnType();

            // TODO execute sql
            if (Collection.class.isAssignableFrom(returnType)) {
                // collection
                result = selectList(statementKey, args);
            } else if (Map.class.isAssignableFrom(returnType)) {
                // map
                //selectList(statementKey, args);
            } else {
                // Object
                result = selectOne(statementKey, args);
            }
            return result;
        });
    }


    public List<Object> selectList(String statementKey, Object[] args) {

        MapperStatement mapperStatement = configuration.getMapperStatementMap().get(statementKey);
        // execute sql
        return executor.query(mapperStatement, args);
    }


    public Object selectOne(String statementKey, Object[] args) {


        return null;
    }


}
