package com.github.byference.tinyim.core.client;

import com.github.byference.tinyim.core.client.handler.*;
import com.github.byference.tinyim.core.codec.PacketCodecHandler;
import com.github.byference.tinyim.core.codec.TinyImSplits;
import com.github.byference.tinyim.core.console.ConsoleCommandManager;
import com.github.byference.tinyim.core.constant.DefaultNettyConst;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

/**
 * TinyImClient
 *
 * @author byference
 * @since 2019-12-29
 */
public class TinyImClient {


    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            final ChannelFuture future = bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline()
                                    .addLast(new TinyImSplits())
                                    .addLast(new HeartBeatTimerHandler())
                                    .addLast(PacketCodecHandler.INSTANCE)
                                    .addLast(LoginResponseHandler.INSTANCE)
                                    .addLast(HeartBeatResponseHandler.INSTANCE)
                                    .addLast(MessageResponseHandler.INSTANCE)
                                    .addLast(LogoutResponseHandler.INSTANCE)
                                    .addLast(CreateGroupResponseHandler.INSTANCE)
                                    .addLast(JoinGroupResponseHandler.INSTANCE)
                                    .addLast(GroupNotificationResponseHandler.INSTANCE)
                                    .addLast(GroupMessageResponseHandler.INSTANCE);
                            // 启动控制台输入
                            startConsoleInput(channel);
                        }
                    }).connect(DefaultNettyConst.DEFAULT_HOST, DefaultNettyConst.DEFAULT_PORT).sync();

            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    private static void startConsoleInput(SocketChannel channel) {
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
        Scanner scanner = new Scanner(System.in);
        new Thread(() -> {
            for (;;) {
                consoleCommandManager.exec(scanner, channel);
            }
        }).start();
    }
}
