package com.github.byference.mybatis.core;

import java.lang.reflect.Proxy;
import java.util.Collection;
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

        //executor.

        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, (proxy, method, args) -> {

            Object result = null;
            String statementKey = clazz.getName() + "." + method.getName();

            Class<?> returnType = method.getReturnType();

            if (Collection.class.isAssignableFrom(returnType)) {
                // collection
                result = this.configuration.selectList(statementKey, args);
            } else if (Map.class.isAssignableFrom(returnType)) {
                // map
                //this.configuration.selectList(statementKey, args);
            } else {
                // Object
                result = this.configuration.selectOne(statementKey, args);
            }


            // TODO 执行sql
            System.out.println("execute sql");

            return result;
        });
    }


}
