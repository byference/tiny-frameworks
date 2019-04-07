package com.github.byference.tinyrpc.provider.rpc;

import com.github.byference.tinyrpc.core.annotation.TinyService;
import com.github.byference.tinyrpc.core.common.TinyRpcConst;
import com.github.byference.tinyrpc.core.model.RpcRequest;
import com.github.byference.tinyrpc.core.model.RpcResponse;
import com.github.byference.tinyrpc.core.util.RpcDecoder;
import com.github.byference.tinyrpc.core.util.RpcEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * RpcServer
 *
 * @author byference
 * @since 2019/04/01
 */
@Slf4j
@Component
public class RpcServer implements ApplicationContextAware, InitializingBean {


    private final Map<String, Object> rpcServiceBeans = new ConcurrentHashMap<>();

    private static ExecutorService handleExecutor;

    private static ExecutorService listenerExecutor;


    /**
     * 在Spring容器启动完成后会执行该方法
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        // 获取 TinyService 注解暴露的服务
        Map<String, Object> rpcServices = applicationContext.getBeansWithAnnotation(TinyService.class);
        if (!CollectionUtils.isEmpty(rpcServices)) {

            rpcServices.forEach((beanName, bean) -> {
                String serviceName = bean.getClass().getAnnotation(TinyService.class).value();
                if (StringUtils.isEmpty(serviceName)) {
                    serviceName = beanName;
                }
                rpcServiceBeans.put(serviceName, bean);
            });
        }
        log.info("==> RPC容器初始化成功");
    }


    /**
     * 初始化完成后启动 RpcServer
     */
    @Override
    public void afterPropertiesSet() {

        if (listenerExecutor == null) {
            synchronized (RpcServer.class) {
                if (listenerExecutor == null) {
                    listenerExecutor = Executors.newFixedThreadPool(1);
                }
            }
        }
        listenerExecutor.submit(this::start);
    }

    private void start() {

        String serverAddress = TinyRpcConst.SERVER_ADDRESS;
        ServerBootstrap server = new ServerBootstrap();
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            server.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) {
                            channel.pipeline()
                                    .addLast(new RpcDecoder(RpcRequest.class))
                                    .addLast(new RpcEncoder(RpcResponse.class))
                                    .addLast(new RpcServerHandler(rpcServiceBeans));
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            String[] array = serverAddress.split(":");
            String host = array[0];
            int port = Integer.parseInt(array[1]);
            ChannelFuture future = server.bind(host, port).sync();

            log.info("==> RpcServer[ {} ] 启动成功", future.channel().localAddress());
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("==> RpcServer启动失败: " + e.getMessage(), e);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


    public static void submit(Runnable task) {

        if (handleExecutor == null) {
            synchronized (RpcServer.class) {
                if (handleExecutor == null) {
                    handleExecutor = Executors.newFixedThreadPool(16);
                }
            }
        }
        handleExecutor.submit(task);
    }
}
