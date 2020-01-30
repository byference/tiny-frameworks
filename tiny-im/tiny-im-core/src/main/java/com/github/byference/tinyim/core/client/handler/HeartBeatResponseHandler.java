package com.github.byference.tinyim.core.client.handler;

import com.github.byference.tinyim.core.protocol.response.HeartBeatResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * HeartBeatResponseHandler
 *
 * @author byference
 * @since 2020-01-29
 */
@ChannelHandler.Sharable
public class HeartBeatResponseHandler extends SimpleChannelInboundHandler<HeartBeatResponsePacket> {

    public static HeartBeatResponseHandler INSTANCE = new HeartBeatResponseHandler();

    private HeartBeatResponseHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatResponsePacket packet) {
        System.out.println("heartbeat...\n");
    }
}
