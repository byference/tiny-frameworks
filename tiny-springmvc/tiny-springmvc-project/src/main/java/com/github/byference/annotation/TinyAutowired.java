package com.github.byference.annotation;

import java.lang.annotation.*;

/**
 * Marks a field as to be autowired by ioc.
 *
 * @author byference
 * @since 2019/03/20
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TinyAutowired {

    /**
     * Declares whether the annotated dependency is required.
     */
    boolean required() default true;

    /**
     * The value may indicate a suggestion for a logical component name.
     */
    String value() default "";

}
