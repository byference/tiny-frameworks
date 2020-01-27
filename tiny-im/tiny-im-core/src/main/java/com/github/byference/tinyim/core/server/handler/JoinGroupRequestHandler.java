package com.github.byference.tinyim.core.server.handler;

import com.github.byference.tinyim.core.protocol.request.JoinGroupRequestPacket;
import com.github.byference.tinyim.core.protocol.response.GroupNotificationResponsePacket;
import com.github.byference.tinyim.core.protocol.response.JoinGroupResponsePacket;
import com.github.byference.tinyim.core.util.SessionHolder;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * JoinGroupRequestHandler
 *
 * @author byference
 * @since 2020-01-27
 */
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket requestPacket) {

        JoinGroupResponsePacket responsePacket = new JoinGroupResponsePacket();
        responsePacket.setSuccess(false);

        boolean isContainsGroupName = SessionHolder.containsGroupName(requestPacket.getGroupName());
        if (!isContainsGroupName) {
            responsePacket.setMessage("未找到该群组");
        } else {
            List<String> userList = requestPacket.getUserList();
            userList.add(SessionHolder.getCurrentUser(ctx.channel()));
            SessionHolder.joinGroup(requestPacket.getGroupName(), userList);
            responsePacket.setSuccess(true);
            responsePacket.setMessage("加入成功");
            ctx.channel().writeAndFlush(responsePacket);

            // join-group message
            ChannelGroup channelGroup = SessionHolder.getGroup(requestPacket.getGroupName());
            GroupNotificationResponsePacket notification = new GroupNotificationResponsePacket();
            notification.setGroupName(requestPacket.getGroupName());
            notification.setNotification(String.format("[ %s ] 加入群聊...", StringUtils.join(requestPacket.getUserList(), ",")));
            channelGroup.writeAndFlush(notification);
        }
    }
}
