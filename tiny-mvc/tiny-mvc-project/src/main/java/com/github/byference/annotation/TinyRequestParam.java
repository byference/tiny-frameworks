package com.github.byference.annotation;

import java.lang.annotation.*;

/**
 * Annotation which indicates that a method parameter
 * should be bound to a web request parameter.
 *
 * @author byference
 * @since 2019/03/23
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TinyRequestParam {

    /**
     * The name of the request parameter to bind to.
     */
    String value();
}
