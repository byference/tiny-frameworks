package io.github.byference.spring.beans.factory;

/**
 * TinyBeanFactory
 *
 * @author byference
 * @since 2020-09-26
 */
public interface TinyBeanFactory {

    <T> T getBean(Class<T> requiredType);

    <T> T getBean(String beanName);

}
