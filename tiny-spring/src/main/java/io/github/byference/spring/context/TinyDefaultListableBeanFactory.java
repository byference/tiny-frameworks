package io.github.byference.spring.context;

import io.github.byference.spring.beans.factory.TinyBeanFactory;

/**
 * TinyDefaultListableBeanFactory
 *
 * @author byference
 * @since 2020-09-26
 */
public class TinyDefaultListableBeanFactory implements TinyBeanFactory {

    @Override
    public <T> T getBean(Class<T> requiredType) {
        return null;
    }

    @Override
    public <T> T getBean(String beanName) {
        return null;
    }
}
