 package com.github.byference.tinyim.core.client.handler;

import com.github.byference.tinyim.core.protocol.response.JoinGroupResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * JoinGroupResponseHandler
 *
 * @author byference
 * @since 2020-01-27
 */
@ChannelHandler.Sharable
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {

    public static JoinGroupResponseHandler INSTANCE = new JoinGroupResponseHandler();

    private JoinGroupResponseHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket responsePacket) {

        if (responsePacket.isSuccess()) {
            System.out.println("群聊加入成功...");
        } else {
            System.err.println(responsePacket.getMessage());
        }
    }
}
