package io.github.byference.spring.beans.factory;

import io.github.byference.spring.beans.TinyBeansException;

import java.util.Map;

/**
 * TinyListableBeanFactory
 *
 * @author byference
 * @since 2020-12-13
 */
public interface TinyListableBeanFactory extends TinyBeanFactory {

    String[] getBeanDefinitionNames();

    String[] getBeanNamesForType(Class<?> type);

    <T> Map<String, T> getBeansOfType(Class<T> type) throws TinyBeansException;
}
