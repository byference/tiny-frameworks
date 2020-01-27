package com.github.byference.tinyim.core.client.handler;

import com.github.byference.tinyim.core.protocol.response.GroupNotificationResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * GroupNotificationResponseHandler
 *
 * @author byference
 * @since 2020-01-27
 */
public class GroupNotificationResponseHandler extends SimpleChannelInboundHandler<GroupNotificationResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupNotificationResponsePacket packet) {
        System.out.println("==========================  收到来自群 [ " + packet.getGroupName() + " ] 的消息  ==========================");
        System.out.println(packet.getNotification());
        System.out.println("=============================================================================");
    }
}
