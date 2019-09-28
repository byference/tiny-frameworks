package com.github.byference.tinyrpc.core.rpc;

import com.github.byference.tinyrpc.core.common.TinyRpcConst;
import com.github.byference.tinyrpc.core.model.RpcRequest;
import com.github.byference.tinyrpc.core.model.RpcResponse;
import com.github.byference.tinyrpc.core.util.RpcDecoder;
import com.github.byference.tinyrpc.core.util.RpcEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * RPC通信client
 *
 * @author byference
 * @since 2019/04/02
 */
@Slf4j
public class RpcClient extends SimpleChannelInboundHandler<RpcResponse> {

    private RpcRequest rpcRequest;

    private RpcResponse rpcResponse;

    private final CountDownLatch countDownLatch = new CountDownLatch(1);

    RpcClient(RpcRequest rpcRequest) {
        this.rpcRequest = rpcRequest;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext context, RpcResponse rpcResponse) {

        log.info("rpc server [ {} ] error : {}", context.channel().remoteAddress(), rpcResponse);
        this.rpcResponse = rpcResponse;
        context.flush();
        countDownLatch.countDown();
    }

    /**
     * 发送消息
     */
    RpcResponse send() {
        Bootstrap client = new Bootstrap();
        NioEventLoopGroup loopGroup = new NioEventLoopGroup();
        try {
            client.group(loopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) {
                            channel.pipeline()
                                    .addLast(new RpcEncoder(RpcRequest.class))
                                    .addLast(new RpcDecoder(RpcResponse.class))
                                    .addLast(this);
                        }
                    }).option(ChannelOption.SO_KEEPALIVE, true);

            String serverAddress = TinyRpcConst.SERVER_ADDRESS;
            String host = serverAddress.split(":")[0];
            int port = Integer.parseInt(serverAddress.split(":")[1]);

            ChannelFuture future = client.connect(host, port).sync();
            log.info("==> 客户端发送数据: {}", rpcRequest);
            future.channel().writeAndFlush(rpcRequest).sync();
            countDownLatch.await();
            future.channel().closeFuture().sync();

        } catch (Exception e) {
            log.error("rpc client send error: " + e.getMessage(), e);
        } finally {
            loopGroup.shutdownGracefully();
        }
        return rpcResponse;
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
        log.error("rpc server [ {} ] error : {}", context.channel().remoteAddress(), cause.getMessage());
        context.close();
    }

}
