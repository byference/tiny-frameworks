package com.github.byference.transactional.ext;

import com.github.byference.transactional.TransactionalAop;
import com.github.byference.transactional.annotation.TinyTransactional;
import com.github.byference.transactional.interceptor.TransactionInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Arrays;

/**
 * TransactionalProxyBeanPostProcessor
 * You can also do it in a simple way -> {@link TransactionalAop}
 *
 * @author byference
 * @since 2019-09-08
 */
@Slf4j
@Component
public class TransactionalProxyBeanPostProcessor implements BeanPostProcessor {

    @Autowired
    private PlatformTransactionManager platformTransactionManager;


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        Class<?> clazz = bean.getClass();
        if (isTransactionalPresent(clazz)) {

            log.info("==> create proxy for {}", beanName);
            return new TransactionInterceptor(bean, platformTransactionManager).getInstance();
        }
        return bean;
    }


    private boolean isTransactionalPresent(Class<?> clazz) {
        return Arrays.stream(clazz.getMethods())
                .anyMatch(method -> method.isAnnotationPresent(TinyTransactional.class));
    }

}
