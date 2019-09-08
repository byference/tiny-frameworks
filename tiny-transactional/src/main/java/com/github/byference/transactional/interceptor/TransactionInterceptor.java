package com.github.byference.transactional.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.lang.reflect.Method;

/**
 * TransactionInterceptor
 *
 * @author byference
 * @since 2019-09-08
 */
@Slf4j
public class TransactionInterceptor implements MethodInterceptor {


    private final Object target;

    private final PlatformTransactionManager txManager;


    public TransactionInterceptor(Object target, PlatformTransactionManager txManager) {
        this.target = target;
        this.txManager = txManager;
    }


    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        // 事务处理
        Object retVal = null;

        DefaultTransactionDefinition txDefinition = new DefaultTransactionDefinition();
        txDefinition.setPropagationBehavior(DefaultTransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus txStatus = txManager.getTransaction(txDefinition);

        try {
            retVal = method.invoke(target, args);
            txManager.commit(txStatus);
        } catch (Exception e) {
            // target invocation exception
            // completeTransactionAfterThrowing(txInfo, ex);
            txManager.rollback(txStatus);
            throw e;
        } finally {
            // cleanupTransactionInfo(txInfo);
        }

        // commitTransactionAfterReturning(txInfo);
        return retVal;
    }


    @SuppressWarnings("unchecked")
    public <T> T getInstance() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return (T) enhancer.create();
    }

}
