package com.github.byference.tinyim.core.server.handler;

import com.github.byference.tinyim.core.protocol.request.HeartBeatRequestPacket;
import com.github.byference.tinyim.core.protocol.response.HeartBeatResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * HeartBeatRequestHandler
 *
 * @author byference
 * @since 2020-01-29
 */
@ChannelHandler.Sharable
public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatRequestPacket> {

    public static HeartBeatRequestHandler INSTANCE = new HeartBeatRequestHandler();

    private HeartBeatRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequestPacket packet) {
        ctx.channel().writeAndFlush(new HeartBeatResponsePacket());
    }
}
