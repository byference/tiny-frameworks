package com.github.byference.handler;

import com.github.byference.annotation.TinyComponent;
import com.github.byference.resolver.ParamResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 参数处理实现
 *
 * @author byference
 * @since 2019/03/23
 */
@TinyComponent("paramHandlerAdapter")
public class ParamHandlerAdapter implements HandlerAdapter {

    @Override
    public Object[] handle(HttpServletRequest request, HttpServletResponse response,
                           Method method, Map<String, Object> beans) {

        // 获取方法定义的参数
        Class<?>[] parameterTypes = method.getParameterTypes();
        // 返回结果
        Object[] result = new Object[parameterTypes.length];

        Map<String, Object> paramResolvers = getBeansOfType(beans, ParamResolver.class);

        int paramIndex = 0;
        int i = 0;

        for (Class<?> parameterType : parameterTypes) {
            for (Map.Entry<String, Object> entry : paramResolvers.entrySet()) {
                ParamResolver paramResolver = (ParamResolver) entry.getValue();
                if (paramResolver.support(parameterType, paramIndex, method)) {
                    result[i++] = paramResolver.resolver(request, response, parameterType, paramIndex, method);
                }
            }
            paramIndex++;
        }
        return result;
    }


    private Map<String, Object> getBeansOfType(Map<String, Object> beans, Class<ParamResolver> paramResolverClass) {

        Map<String, Object> result = new HashMap<>();
        beans.forEach((k, v) -> {
            Class<?>[] interfaces = v.getClass().getInterfaces();
            for (Class<?> anInterface : interfaces) {
                if (anInterface.isAssignableFrom(paramResolverClass)) {
                    result.put(k, v);
                }
            }
        });
        return result;
    }

}
