package com.github.byference.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 参数处理接口 {@link ParamHandlerAdapter}
 *
 * @author byference
 * @since 2019/03/23
 */
public interface HandlerAdapter {

    /**
     * 参数处理方法
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @param method  {@link Method}
     * @param beans  ioc容器
     * @return 方法参数列表
     */
    Object[] handle(HttpServletRequest request, HttpServletResponse response,
                           Method method, Map<String, Object> beans);
}
