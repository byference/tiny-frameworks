package com.github.byference.tinyim.core.server.handler;

import com.github.byference.tinyim.core.protocol.request.GroupMessageRequestPacket;
import com.github.byference.tinyim.core.protocol.response.GroupMessageResponsePacket;
import com.github.byference.tinyim.core.util.SessionHolder;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * GroupMessageRequestHandler
 *
 * @author byference
 * @since 2020-01-27
 */
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket requestPacket) {

        ChannelGroup channelGroup = SessionHolder.getGroup(requestPacket.getGroupName());
        GroupMessageResponsePacket responsePacket = new GroupMessageResponsePacket();
        responsePacket.setGroupName(requestPacket.getGroupName());
        responsePacket.setMessage(requestPacket.getMessage());
        responsePacket.setMessageReceivedTime(requestPacket.getMessageReceivedTime());
        responsePacket.setSender(SessionHolder.getCurrentUser(ctx.channel()));
        channelGroup.writeAndFlush(responsePacket);
    }
}
