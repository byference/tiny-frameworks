package com.github.byference.transactional.annotation;

import java.lang.annotation.*;

/**
 * TinyTransactional
 *
 * @author byference
 * @since 2019-09-08
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface TinyTransactional {
}
