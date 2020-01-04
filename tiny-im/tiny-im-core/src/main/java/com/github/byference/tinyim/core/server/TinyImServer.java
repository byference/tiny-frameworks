package com.github.byference.tinyim.core.server;

import com.github.byference.tinyim.core.codec.PacketDecoder;
import com.github.byference.tinyim.core.codec.PacketEncoder;
import com.github.byference.tinyim.core.constant.DefaultNettyConst;
import com.github.byference.tinyim.core.server.handler.LoginRequestHandler;
import com.github.byference.tinyim.core.server.handler.MessageRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * TinyImServer
 *
 * @author byference
 * @since 2019-12-29
 */
public class TinyImServer {


    public static void main(String[] args) {
        ServerBootstrap bootstrap = new ServerBootstrap();
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            ChannelFuture future = bootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel channel) throws Exception {

                            channel.pipeline()
                                    .addLast(new PacketDecoder())
                                    .addLast(new LoginRequestHandler())
                                    .addLast(new MessageRequestHandler())
                                    .addLast(new PacketEncoder());
                        }
                    }).bind(DefaultNettyConst.DEFAULT_PORT).sync();

            future.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
