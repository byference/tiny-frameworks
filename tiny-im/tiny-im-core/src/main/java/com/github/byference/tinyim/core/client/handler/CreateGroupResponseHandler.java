package com.github.byference.tinyim.core.client.handler;

import com.github.byference.tinyim.core.protocol.response.CreateGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * CreateGroupResponseHandler
 *
 * @author byference
 * @since 2020-01-26
 */
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket packet) {
        if (packet.isSuccess()) {
            System.out.println("创建成功：" + packet.getMessage());
        } else {
            System.err.println("创建失败：" + packet.getMessage());
        }
    }
}
