package com.github.byference.tinyrpc.core.listener;

import com.github.byference.tinyrpc.core.annotation.TinyReference;
import com.github.byference.tinyrpc.core.rpc.RpcProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * RPC上下文监听
 *
 * @author byference
 * @since 2019/04/13
 */
@Slf4j
public class RpcApplicationContextListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
        if (applicationContext.getParent() == null) {

            Map<String, Object> components = applicationContext.getBeansWithAnnotation(Controller.class);
            components.forEach((beanName, bean) -> {
                Field[] fields = bean.getClass().getDeclaredFields();
                for (Field field : fields) {
                    if (field.isAnnotationPresent(TinyReference.class)) {
                        String name = field.getAnnotation(TinyReference.class).value();
                        if (StringUtils.isEmpty(name)) {
                            name = field.getName();
                        }
                        log.info("==> rpc reference inject [ {} ]", name);

                        // 创建动态代理对象
                        Object instance = RpcProxy.getInstance(field.getType());
                        try {
                            field.setAccessible(true);
                            field.set(bean, instance);
                        } catch (IllegalAccessException e) {
                            log.error("rpc reference inject error: " + e.getMessage(), e);
                        }
                    }
                }
            });
        }
    }

}
