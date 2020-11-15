package io.github.byference.spring.beans.support;

import io.github.byference.spring.beans.factory.config.TinyBeanDefinition;
import io.github.byference.spring.util.StringUtil;
import lombok.SneakyThrows;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * TinyAnnotatedBeanDefinitionReader
 *
 * @author byference
 * @since 2020-09-26
 */
public class TinyAnnotatedBeanDefinitionReader {

    private List<TinyBeanDefinition> beanDefinitions = new ArrayList<>();

    private List<String> classNames = new ArrayList<>();

    @SneakyThrows
    public void register(Class<?>[] componentClasses) {

        // scan class
        for (Class<?> componentClass : componentClasses) {
            String basePackage = componentClass.getName().replace("." + componentClass.getSimpleName(), "");
            scanner(basePackage);
        }

        for (String className : classNames) {
            Class<?> componentClass = Class.forName(className);
            if (componentClass.isInterface()) {
                continue;
            }
            // 包装成 beanDefinition，并存储到list中
            beanDefinitions.add(createBeanDefinition(componentClass));
        }
    }

    public List<TinyBeanDefinition> loadBeanDefinition() {
        return beanDefinitions;
    }

    /**
     * 创建 TinyBeanDefinition
     * @param componentClass 组件类
     * @return TinyBeanDefinition
     */
    private TinyBeanDefinition createBeanDefinition(Class<?> componentClass) {
        TinyBeanDefinition bd = new TinyBeanDefinition();
        bd.setSourceClass(componentClass);
        bd.setBeanClassName(componentClass.getName());
        bd.setFactoryBeanName(StringUtil.toLowFirstCase(componentClass.getSimpleName()));
        return bd;
    }

    private void scanner(String basePackage) {
        URL resource = this.getClass().getClassLoader().getResource(basePackage.replaceAll("\\.", "/"));
        if (resource == null) {
            return;
        }
        File dir = new File(resource.getFile());
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                scanner(basePackage + "." + file.getName());
            } else {
                String className = basePackage + "." + file.getName().replaceAll(".class", "");
                classNames.add(className);
            }
        }
    }

}
