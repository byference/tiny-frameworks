package com.github.byference.tinyrpc.annotation;


import java.lang.annotation.*;

/**
 * Reference
 *
 * @author byference
 * @since 2019/03/31
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TinyReference {

    /**
     * Interface class name
     */
    String value() default "";

    /**
     * Service version, default value is empty string
     */
    String version() default "";

}
