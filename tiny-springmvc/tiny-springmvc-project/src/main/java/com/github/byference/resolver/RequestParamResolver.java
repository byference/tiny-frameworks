package com.github.byference.resolver;

import com.github.byference.annotation.TinyComponent;
import com.github.byference.annotation.TinyRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 处理 {@link HttpServletRequest#getParameter} 参数
 *
 * @author byference
 * @since 2019/03/23
 */
@TinyComponent
public class RequestParamResolver implements ParamResolver {

    @Override
    public boolean support(Class<?> type, int paramIndex, Method method) {

        Annotation[][] parameterAnnotationsList = method.getParameterAnnotations();
        Annotation[] parameterAnnotations = parameterAnnotationsList[paramIndex];
        for (Annotation parameterAnnotation : parameterAnnotations) {
            if (TinyRequestParam.class.isAssignableFrom(parameterAnnotation.getClass())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object resolver(HttpServletRequest request, HttpServletResponse response,
                           Class<?> type, int paramIndex, Method method) {
        Annotation[][] parameterAnnotationsList = method.getParameterAnnotations();
        Annotation[] parameterAnnotations = parameterAnnotationsList[paramIndex];
        for (Annotation parameterAnnotation : parameterAnnotations) {
            if (TinyRequestParam.class.isAssignableFrom(parameterAnnotation.getClass())) {
                TinyRequestParam tinyRequestParam = (TinyRequestParam) parameterAnnotation;
                return request.getParameter(tinyRequestParam.value());
            }
        }
        return null;
    }

}
