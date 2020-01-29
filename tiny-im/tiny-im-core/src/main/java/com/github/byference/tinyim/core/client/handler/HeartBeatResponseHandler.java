package com.github.byference.tinyim.core.client.handler;

import com.github.byference.tinyim.core.protocol.response.HeartBeatResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * HeartBeatResponseHandler
 *
 * @author byference
 * @since 2020-01-29
 */
public class HeartBeatResponseHandler extends SimpleChannelInboundHandler<HeartBeatResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatResponsePacket packet) {
        System.out.println("heartbeat...\n");
    }
}
