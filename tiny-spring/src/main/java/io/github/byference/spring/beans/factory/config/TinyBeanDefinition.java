package io.github.byference.spring.beans.factory.config;

import lombok.Data;

/**
 * TinyBeanDefinition
 *
 * @author byference
 * @since 2020-09-26
 */
@Data
public class TinyBeanDefinition {

    private Class<?> sourceClass;

    private String beanClassName;

    private String factoryBeanName;

}
