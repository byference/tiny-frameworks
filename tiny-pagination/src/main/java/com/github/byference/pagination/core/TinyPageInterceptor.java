package com.github.byference.pagination.core;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * TinyPageInterceptor
 *
 * @author byference
 * @since 2019-08-24
 */
@Slf4j
@Component
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class})
})
public class TinyPageInterceptor implements Interceptor {

    private volatile TinyPageHelper dialect;


    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        log.info("tiny page interceptor start...");
        Page localPage = TinyPageHelper.getLocalPage();
        if (localPage == null) {
            return invocation.proceed();
        }

        // 检查TinyPageHelper 是否初始化
        this.checkDialectExists();

        Executor executor = (Executor) invocation.getTarget();
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        Object parameter = args[1];
        RowBounds rowBounds = (RowBounds) args[2];
        ResultHandler resultHandler = (ResultHandler) args[3];

        CacheKey cacheKey;
        BoundSql boundSql;
        if (args.length == 4) {
            // 4个参数时
            boundSql = ms.getBoundSql(parameter);
            cacheKey = executor.createCacheKey(ms, parameter, rowBounds, boundSql);
        } else {
            // 6个参数时
            cacheKey = (CacheKey) args[4];
            boundSql = (BoundSql) args[5];
        }


        // 执行 count 查询
        String oldSql = boundSql.getSql();
        // 简单处理一下sql
        String countSql = oldSql.replaceAll("SELECT.*?FROM", "SELECT count(1) FROM ");

        MappedStatement mappedStatement = this.newCountMappedStatement(ms, ms.getId() + "_COUNT");
        BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSql, boundSql.getParameterMappings(), parameter);

        List<Long> countList = executor.query(mappedStatement, parameter, rowBounds, resultHandler, null, countBoundSql);
        if (!CollectionUtils.isEmpty(countList)) {
            localPage.setTotal(countList.get(0));
        } else {
            return new ArrayList<>();
        }


        // 执行分页 (目前只支持MySQL)
        String pageSql = this.getPageSql(boundSql, localPage);
        BoundSql pageBoundSql = new BoundSql(ms.getConfiguration(), pageSql, boundSql.getParameterMappings(), parameter);
        List<Object> result = executor.query(ms, parameter, rowBounds, resultHandler, cacheKey, pageBoundSql);

        // 封装成 Page对象
        localPage.addAll(result);
        return localPage;
    }


    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }


    @Override
    public void setProperties(Properties properties) {

        // 初始化 TinyPageHelper
        dialect = new TinyPageHelper();
    }


    /**
     * Spring bean 方式配置时，如果没有配置属性就不会执行 {@link #setProperties} 进行初始化
     */
    private void checkDialectExists() {

        if (dialect == null) {
            synchronized (TinyPageHelper.class) {
                if (dialect == null) {
                    setProperties(new Properties());
                }
            }
        }
    }


    /**
     * 获取分页SQL
     */
    private String getPageSql(BoundSql boundSql, Page localPage) {

        return boundSql.getSql() + String.format(" limit %s,%s ", localPage.getPageNum(), localPage.getPageSize());
    }


    /**
     * create count query {@link MappedStatement}
     *
     * @param ms      source {@link MappedStatement}
     * @param newMsId new MappedStatement ID
     * @return new {@link MappedStatement}
     */
    private MappedStatement newCountMappedStatement(MappedStatement ms, String newMsId) {

        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), newMsId, ms.getSqlSource(), ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());

        if (ms.getKeyProperties() != null && ms.getKeyProperties().length > 0) {
            builder.keyProperty(String.join(",", ms.getKeyProperties()));
        }

        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        // 修改 count 查询返回值类型为Long
        List<ResultMap> resultMaps = new ArrayList<>();
        ResultMap resultMap = new ResultMap.Builder(ms.getConfiguration(), ms.getId(), Long.class, new ArrayList<>()).build();
        resultMaps.add(resultMap);

        builder.resultMaps(resultMaps);
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());

        return builder.build();
    }

}