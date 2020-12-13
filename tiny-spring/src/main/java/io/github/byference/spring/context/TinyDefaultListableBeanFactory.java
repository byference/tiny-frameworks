package io.github.byference.spring.context;

import io.github.byference.spring.beans.TinyBeanWrapper;
import io.github.byference.spring.beans.factory.TinyBeanFactory;
import io.github.byference.spring.beans.factory.config.TinyBeanDefinition;
import io.github.byference.spring.stereotype.TinyAutowired;
import io.github.byference.spring.stereotype.TinyComponent;
import io.github.byference.spring.util.StringUtil;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TinyDefaultListableBeanFactory
 *
 * @author byference
 * @since 2020-09-26
 */
public class TinyDefaultListableBeanFactory implements TinyBeanFactory {

    private final Map<String, TinyBeanWrapper> factoryBeanObjectCache = new ConcurrentHashMap<>();

    private final Map<String, Object> factoryBeanInstanceCache = new ConcurrentHashMap<>();

    private final Map<String, TinyBeanDefinition> beanDefinitionMap;

    public TinyDefaultListableBeanFactory(Map<String, TinyBeanDefinition> beanDefinitionMap) {
        this.beanDefinitionMap = beanDefinitionMap;
    }


    @Override
    public <T> T getBean(Class<T> requiredType) {
        return this.getBean(StringUtil.toLowFirstCase(requiredType.getSimpleName()));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getBean(String beanName) {
        TinyBeanWrapper tinyBeanWrapper = factoryBeanObjectCache.get(beanName);

        if (tinyBeanWrapper == null) {
            TinyBeanDefinition tinyBeanDefinition = beanDefinitionMap.get(beanName);
            // 实例化
            Object instance = instantiateBean(tinyBeanDefinition);
            factoryBeanInstanceCache.put(beanName, instance);

            // beanWrapper
            tinyBeanWrapper = new TinyBeanWrapper(instance);
            factoryBeanObjectCache.put(beanName, tinyBeanWrapper);

            // populateBean
            populateBean(instance);
        }

        // return wrapper instance
        return (T) tinyBeanWrapper.getInstance();
    }

    @SneakyThrows
    private void populateBean(Object instance) {
        Class<?> clazz = instance.getClass();
        // handle TinyComponent only
        if (!clazz.isAnnotationPresent(TinyComponent.class)) {
            return;
        }

        final Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(TinyAutowired.class)) {
                TinyAutowired tinyAutowired = field.getAnnotation(TinyAutowired.class);
                String tinyAutowiredBeanName = tinyAutowired.value().trim();
                if (StringUtil.isEmpty(tinyAutowiredBeanName)) {
                    tinyAutowiredBeanName = StringUtil.toLowFirstCase(field.getType().getSimpleName());
                }
                field.setAccessible(true);
                field.set(instance, getBean(tinyAutowiredBeanName));
            }
        }
    }

    @SneakyThrows
    private Object instantiateBean(TinyBeanDefinition tinyBeanDefinition) {
        return tinyBeanDefinition.getSourceClass().getDeclaredConstructor().newInstance();
    }
}
