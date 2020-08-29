package io.github.byference.mapper.core;

import java.lang.annotation.*;

/**
 * TableName
 *
 * @author byference
 * @since 2020-08-29
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TableName {

    String value();
}
