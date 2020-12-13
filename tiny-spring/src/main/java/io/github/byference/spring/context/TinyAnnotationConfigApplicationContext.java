package io.github.byference.spring.context;

import io.github.byference.spring.beans.factory.config.TinyBeanDefinition;
import io.github.byference.spring.beans.support.TinyAnnotatedBeanDefinitionReader;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TinyAnnotationConfigApplicationContext
 *
 * @author byference
 * @since 2020-10-08
 */
public class TinyAnnotationConfigApplicationContext extends TinyAbstractApplicationContext {

    private final TinyAnnotatedBeanDefinitionReader reader;

    private final Map<String, TinyBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    private TinyDefaultListableBeanFactory beanFactory;

    public TinyAnnotationConfigApplicationContext() {
        this.reader = new TinyAnnotatedBeanDefinitionReader();
    }

    public void register(Class<?>... componentClasses) {
        this.reader.register(componentClasses);
    }

    @Override
    public void refresh() {
        // refresh 的时候 从 reader 中获取 所有的beanDefinition 注册到spring容器中
        List<TinyBeanDefinition> tinyBeanDefinitions = reader.loadBeanDefinition();

        // 注册
        doRegisterBeanDefinition(tinyBeanDefinitions);

        // 依赖注入
        doAutowired();

        // 后置处理
        beanPostProcessor();
    }

    @Override
    public <T> T getBean(Class<T> requiredType) {
        return beanFactory.getBean(requiredType);
    }

    @Override
    public <T> T getBean(String beanName) {
        return beanFactory.getBean(beanName);
    }

    private void doRegisterBeanDefinition(List<TinyBeanDefinition> tinyBeanDefinitions) {
        for (TinyBeanDefinition tinyBeanDefinition : tinyBeanDefinitions) {
            if (beanDefinitionMap.containsKey(tinyBeanDefinition.getFactoryBeanName())) {
                throw new IllegalStateException("The bean [" + tinyBeanDefinition.getFactoryBeanName() + "] is exists!");
            }
            beanDefinitionMap.put(tinyBeanDefinition.getFactoryBeanName(), tinyBeanDefinition);
        }
        beanFactory = new TinyDefaultListableBeanFactory(beanDefinitionMap);
    }

    private void doAutowired() {
        beanDefinitionMap.forEach((beanName, tinyBeanDefinition) -> getBean(tinyBeanDefinition.getSourceClass()));
    }

    private void beanPostProcessor() {
        beanDefinitionMap.forEach((beanName, beanDefinition) -> {
            final Object bean = getBean(beanName);
            if (bean instanceof TinyApplicationContextAware) {
                TinyApplicationContextAware tinyApplicationContextAware = (TinyApplicationContextAware) bean;
                tinyApplicationContextAware.setTinyApplicationContext(this);
            }
        });
    }
}
