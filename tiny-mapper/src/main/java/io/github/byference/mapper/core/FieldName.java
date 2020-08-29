package io.github.byference.mapper.core;

import java.lang.annotation.*;

/**
 * FieldName
 *
 * @author byference
 * @since 2020-08-29
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FieldName {

    String value();
}
