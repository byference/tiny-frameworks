package com.github.byference.transactional;

import com.github.byference.transactional.annotation.TinyTransactional;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * TransactionalAop
 *
 * @author byference
 * @since 2019-09-08
 */
//@Component
//@Aspect
public class TransactionalAop {

    @Autowired
    private PlatformTransactionManager txManager;

    @Around("@annotation(transactional)")
    public void around(ProceedingJoinPoint point, TinyTransactional transactional) throws Throwable {

        DefaultTransactionDefinition txDefinition = new DefaultTransactionDefinition();
        txDefinition.setPropagationBehavior(DefaultTransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus txStatus = txManager.getTransaction(txDefinition);

        try {
            point.proceed();
            txManager.commit(txStatus);
        } catch (Exception e) {
            txManager.rollback(txStatus);
            throw e;
        }
    }

}
