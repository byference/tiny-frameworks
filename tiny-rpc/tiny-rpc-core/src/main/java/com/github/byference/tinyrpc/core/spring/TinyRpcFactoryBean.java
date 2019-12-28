package com.github.byference.tinyrpc.core.spring;

import com.github.byference.tinyrpc.core.rpc.RpcProxy;
import org.springframework.beans.factory.FactoryBean;

/**
 * TinyRpcFactoryBean
 *
 * @author byference
 * @since 2019-12-28
 */
public class TinyRpcFactoryBean<T> implements FactoryBean<T> {

    private final Class<?> interfaceClass;

    public TinyRpcFactoryBean(Class<?> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getObject() throws Exception {
        return (T) RpcProxy.getInstance(interfaceClass);
    }

    @Override
    public Class<?> getObjectType() {
        return interfaceClass;
    }
}
