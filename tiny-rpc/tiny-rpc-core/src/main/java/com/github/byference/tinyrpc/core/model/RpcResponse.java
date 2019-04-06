package com.github.byference.tinyrpc.core.model;

import lombok.Data;

/**
 * RPC远程调用：响应
 *
 * @author byference
 * @since 2019/04/01
 */
@Data
public class RpcResponse {

    /**
     * 响应ID
     */
    private String responseId;

    /**
     * 响应的请求ID
     */
    private String requestId;

    /**
     * 响应结果
     */
    private boolean result;

    /**
     * 响应数据
     */
    private Object data;

    /**
     * 响应异常
     */
    private Throwable error;

}
