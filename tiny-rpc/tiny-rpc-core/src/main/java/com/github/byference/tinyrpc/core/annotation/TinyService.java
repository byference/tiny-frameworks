package com.github.byference.tinyrpc.core.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Service annotation
 *
 * @author byference
 * @since 2019/03/31
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface TinyService {

    /**
     * The value may indicate a suggestion for a logical component name.
     */
    String value() default "";

    /**
     * Service version, default value is empty string
     */
    String version() default "";

}
