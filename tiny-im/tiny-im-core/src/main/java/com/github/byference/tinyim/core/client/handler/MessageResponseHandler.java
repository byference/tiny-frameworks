package com.github.byference.tinyim.core.client.handler;

import com.github.byference.tinyim.core.protocol.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * MessageResponseHandler
 *
 * @author byference
 * @since 2020-01-04
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket packet) {

        String message = packet.getMessage();
        String fromUserName = packet.getFromUserName();
        System.out.println("==========================  收到消息  ==========================");
        System.out.printf("%s say: [ %s ]\n", fromUserName, message);
        System.out.println("==============================================================");
    }
}
