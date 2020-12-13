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

    private String beanName;

    private Object instance;

    public TinyBeanWrapper(String beanName, Object instance) {
        this.beanName = beanName;
        this.instance = instance;
    }
}
