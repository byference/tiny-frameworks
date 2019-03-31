package com.github.byference.annotation;

import java.lang.annotation.*;

/**
 * Indicates that an annotated class is a "Controller" (e.g. a web controller).
 *
 * @author byference
 * @since 2019/03/16
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TinyController {

    /**
     * The value may indicate a suggestion for a logical component name
     * @return the suggested component name, if any (or empty String otherwise)
     */
    String value() default "";

}
