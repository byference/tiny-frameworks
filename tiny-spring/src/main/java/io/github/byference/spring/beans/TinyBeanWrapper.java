package io.github.byference.spring.beans;

import lombok.Data;

/**
 * TinyBeanWrapper
 *
 * @author byference
 * @since 2020-09-26
 */
@Data
public class TinyBeanWrapper {

    private Object instance;

    public TinyBeanWrapper(Object instance) {
        this.instance = instance;
    }
}
