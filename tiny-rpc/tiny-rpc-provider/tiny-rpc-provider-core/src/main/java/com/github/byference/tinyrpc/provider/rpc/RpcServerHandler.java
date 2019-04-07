package com.github.byference.tinyrpc.provider.rpc;

import com.github.byference.tinyrpc.core.model.RpcRequest;
import com.github.byference.tinyrpc.core.model.RpcResponse;
import com.github.byference.tinyrpc.core.util.IdGenerator;
import com.github.byference.tinyrpc.core.util.StringUtil;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * RpcServerHandler
 *
 * @author byference
 * @since 2019/04/01
 */
@Slf4j
public class RpcServerHandler extends SimpleChannelInboundHandler<RpcRequest> {

    private Map<String, Object> rpcServiceBeans;

    public RpcServerHandler(Map<String, Object> rpcServiceBeans) {
        this.rpcServiceBeans = rpcServiceBeans;
    }


    @Override
    protected void channelRead0(ChannelHandlerContext context, RpcRequest rpcRequest) {

        RpcServer.submit(() -> {
            log.info("==> RpcServerHandler::channelRead: {}", rpcRequest);

            RpcResponse rpcResponse = handler(rpcRequest);
            context.writeAndFlush(rpcResponse).addListener(ChannelFutureListener.CLOSE);
        });
    }


    /**
     * 响应请求
     */
    private RpcResponse handler(RpcRequest rpcRequest) {

        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setResponseId(IdGenerator.generateUUID());
        rpcResponse.setRequestId(rpcRequest.getRequestId());
        try {
            String className = rpcRequest.getClassName();
            String methodName = rpcRequest.getMethodName();
            Class<?>[] parameterTypes = rpcRequest.getParameterTypes();
            Object[] parameters = rpcRequest.getParameters();

            Class<?> clazz = Class.forName(className);
            String[] split = className.split("\\.");
            String beanName = split[split.length - 1];
            Object serviceBean = rpcServiceBeans.get(StringUtil.lowerFirst(beanName));
            Assert.notNull(serviceBean, "服务获取异常，无" + className + "服务.");

            Method method = clazz.getMethod(methodName, parameterTypes);
            Object data = method.invoke(serviceBean, parameters);
            rpcResponse.setResult(true);
            rpcResponse.setData(data);
        } catch (Exception e) {
            rpcResponse.setResult(false);
            rpcResponse.setError(e);
            log.error("==> RPC调用异常: " + e.getMessage(), e);
        }
        return rpcResponse;
    }
}
