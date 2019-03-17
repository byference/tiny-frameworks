package com.github.byference.annotation;

import java.lang.annotation.*;

/**
 * Annotation for mapping web requests onto methods in request-handling classes
 * with flexible method signatures.
 *
 * @author byference
 * @since 2019/03/16
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TinyRequestMapping {

    /**
     * The primary mapping expressed by this annotation.
     */
    String value() default "";

}
