package com.github.byference.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 参数处理Resolver
 *
 * @author byference
 * @since 2019/03/23
 */
public interface ParamResolver {

    /**
     * 判断当前类是否继承 {@link ParamResolver}
     *
     * @param type       当前参数注解类对象
     * @param paramIndex 参数下标
     * @param method     当前方法
     * @return true:当前类继承自 {@code ParamResolver}
     */
    boolean support(Class<?> type, int paramIndex, Method method);

    /**
     * 获取方法当前下标的参数
     *
     * @param request    {@link HttpServletRequest}
     * @param response   {@link HttpServletResponse}
     * @param type       当前参数注解类对象
     * @param paramIndex 参数下标
     * @param method     当前方法
     * @return 处理后的参数列表
     */
    Object resolver(HttpServletRequest request, HttpServletResponse response,
                    Class<?> type, int paramIndex, Method method);
}
