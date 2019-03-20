package com.github.byference.annotation;

import java.lang.annotation.*;

/**
 * Indicates that an annotated class is a "component".
 *
 * @author byference
 * @since 2019/03/20
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TinyComponent {

    /**
     * The value may indicate a suggestion for a logical component name.
     */
    String value() default "";

}
