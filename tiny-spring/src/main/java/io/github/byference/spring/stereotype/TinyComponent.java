package io.github.byference.spring.stereotype;

import java.lang.annotation.*;

/**
 * TinyComponent
 *
 * @author byference
 * @since 2020-09-26
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TinyComponent {


	String value() default "";

}