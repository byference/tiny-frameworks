package com.github.byference.tinyim.core.client.handler;

import com.github.byference.tinyim.core.protocol.response.CreateGroupResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * CreateGroupResponseHandler
 *
 * @author byference
 * @since 2020-01-26
 */
@ChannelHandler.Sharable
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {

    public static CreateGroupResponseHandler INSTANCE = new CreateGroupResponseHandler();

    private CreateGroupResponseHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket packet) {
        if (packet.isSuccess()) {
            System.out.println("创建成功：" + packet.getMessage());
        } else {
            System.err.println("创建失败：" + packet.getMessage());
        }
    }
}
