package com.github.byference.tinyim.core.client.handler;

import com.github.byference.tinyim.core.protocol.response.GroupMessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * GroupMessageResponseHandler
 *
 * @author byference
 * @since 2020-01-27
 */
public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponsePacket packet) {

        System.out.println("==========================  收到来自群 [ " + packet.getGroupName() + " ] 的消息  ==========================");
        System.out.printf("%s (%s):\n", packet.getSender(), packet.getMessageReceivedTime());
        System.out.println(packet.getMessage());
        System.out.println("=============================================================================");
    }
}
