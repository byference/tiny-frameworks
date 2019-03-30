package com.github.byference.annotation;

import java.lang.annotation.*;

/**
 * This annotation may be used on a field as a qualifier for
 * candidate beans when autowiring.
 *
 * @author byference
 * @since 2019/03/30
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TinyQualifier {


    String value() default "";
}
