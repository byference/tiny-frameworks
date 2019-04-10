package com.github.byference.tinyrpc.core.rpc;

import com.github.byference.tinyrpc.core.model.RpcRequest;
import com.github.byference.tinyrpc.core.model.RpcResponse;
import com.github.byference.tinyrpc.core.util.IdGenerator;

import java.lang.reflect.Proxy;

/**
 * 动态代理类
 *
 * @author byference
 * @since 2019/04/02
 */
public class RpcProxy {

    @SuppressWarnings("unchecked")
    public static <T> T getInstance(Class<T> interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, (proxy, method, args) -> {

            RpcRequest rpcRequest = new RpcRequest();
            String className = method.getDeclaringClass().getName();
            Class<?>[] parameterTypes = method.getParameterTypes();
            rpcRequest.setRequestId(IdGenerator.generateUUID());
            rpcRequest.setClassName(className);
            rpcRequest.setParameterTypes(parameterTypes);
            rpcRequest.setParameters(args);
            rpcRequest.setMethodName(method.getName());
            RpcResponse rpcResponse = new RpcClient(rpcRequest).send();
            return rpcResponse.getData();
        });
    }
}
