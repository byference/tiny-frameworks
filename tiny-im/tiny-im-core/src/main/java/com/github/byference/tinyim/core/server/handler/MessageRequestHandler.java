package com.github.byference.tinyim.core.server.handler;

import com.github.byference.tinyim.core.protocol.request.MessageRequestPacket;
import com.github.byference.tinyim.core.protocol.response.MessageResponsePacket;
import com.github.byference.tinyim.core.util.SessionHolder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * MessageRequestHandler
 *
 * @author byference
 * @since 2020-01-04
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket packet) {

        String toUserId = packet.getToUserId();
        Channel toUserChannel = SessionHolder.getChannel(toUserId);
        MessageResponsePacket responsePacket = new MessageResponsePacket();
        responsePacket.setFromUserId(SessionHolder.getCurrentUser(ctx.channel()));
        responsePacket.setFromUserName(SessionHolder.getCurrentUser(ctx.channel()));
        responsePacket.setMessage(packet.getMessage());

        toUserChannel.writeAndFlush(responsePacket);
    }
}
