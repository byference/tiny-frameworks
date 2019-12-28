package com.github.byference.tinyrpc.core.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

public class TinyRpcScannerConfigurer implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

        TinyRpcBeanDefinitionScanner scanner = new TinyRpcBeanDefinitionScanner(registry);
        scanner.registerFilters();
        scanner.doScan("com.github.byference.tinyrpc.provider.api");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }
}