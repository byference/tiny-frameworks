package com.github.byference.resolver;

import com.github.byference.annotation.TinyComponent;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 处理 {@link HttpServletRequest} 参数
 *
 * @author byference
 * @since 2019/03/23
 */
@TinyComponent("httpServletRequestParamResolver")
public class HttpServletRequestParamResolver implements ParamResolver {

    @Override
    public boolean support(Class<?> type, int paramIndex, Method method) {
        return ServletRequest.class.isAssignableFrom(type);
    }

    @Override
    public Object resolver(HttpServletRequest request, HttpServletResponse response,
                           Class<?> type, int paramIndex, Method method) {
        return request;
    }

}
