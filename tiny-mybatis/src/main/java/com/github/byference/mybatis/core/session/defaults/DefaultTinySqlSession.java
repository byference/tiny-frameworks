package com.github.byference.mybatis.core.session.defaults;

import com.github.byference.mybatis.core.mapping.MapperStatement;
import com.github.byference.mybatis.core.TinyConfiguration;
import com.github.byference.mybatis.core.executor.TinyExecutor;

import java.lang.reflect.Proxy;
import java.util.*;

/**
 * DefaultTinySqlSession
 *
 * @author byference
 * @since 2019-08-03
 */
public class DefaultTinySqlSession {

    private TinyConfiguration configuration;

    private TinyExecutor executor;


    public DefaultTinySqlSession(TinyConfiguration configuration, TinyExecutor executor) {
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


    @SuppressWarnings("unchecked")
    public Map<String, Object> selectMap(String statementKey, Object[] args) {

        Object result = this.selectOne(statementKey, args);
        if (result == null) {
            return Collections.emptyMap();
        }

        // not the best way
        return new HashMap((Map) result);
    }


    public List<Object> selectList(String statementKey, Object[] args) {

        MapperStatement mapperStatement = configuration.getMapperStatementMap().get(statementKey);
        // execute sql
        return executor.query(mapperStatement, args);
    }


    public Object selectOne(String statementKey, Object[] args) {

        List<Object> results = this.selectList(statementKey, args);

        if (results.size() == 1) {
            return results.get(0);
        } else if (results.size() > 1) {
            throw new IllegalArgumentException("Expected one result (or null) to be returned by selectOne(), but found: " + results.size());
        } else {
            return null;
        }
    }


}
