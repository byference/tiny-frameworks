package com.github.byference.tinyrpc.core.model;

import lombok.Data;

/**
 * RPC远程调用：请求
 *
 * @author byference
 * @since 2019/04/01
 */
@Data
public class RpcRequest {

    /**
     * 请求ID
     */
    private String requestId;

    /**
     * 类名
     */
    private String className;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 参数结构
     */
    private Class<?>[] parameterTypes;

    /**
     * 参数值
     */
    private Object[] parameters;

}
