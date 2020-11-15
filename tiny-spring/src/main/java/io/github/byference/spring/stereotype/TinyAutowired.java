package io.github.byference.spring.stereotype;

import java.lang.annotation.*;

/**
 * TinyAutowired
 *
 * @author byference
 * @since 2020-09-26
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TinyAutowired {


	String value() default "";

}