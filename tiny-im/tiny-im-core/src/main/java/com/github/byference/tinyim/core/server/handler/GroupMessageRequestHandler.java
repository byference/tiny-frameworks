package com.github.byference.tinyim.core.server.handler;

import com.github.byference.tinyim.core.constant.OffLineMessageSource;
import com.github.byference.tinyim.core.message.InMemoryOffLineMessageStore;
import com.github.byference.tinyim.core.message.OffLineMessage;
import com.github.byference.tinyim.core.message.OffLineMessageStore;
import com.github.byference.tinyim.core.protocol.request.GroupMessageRequestPacket;
import com.github.byference.tinyim.core.protocol.response.GroupMessageResponsePacket;
import com.github.byference.tinyim.core.util.SessionHolder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Set;

/**
 * GroupMessageRequestHandler
 *
 * @author byference
 * @since 2020-01-27
 */
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket requestPacket) {

        // all group members
        Set<String> groupMembers = SessionHolder.getGroupMembers(requestPacket.getGroupName());
        OffLineMessageStore messageStore = InMemoryOffLineMessageStore.getInstance();

        groupMembers.forEach(user -> {
            Channel currentChannel = SessionHolder.getChannel(user);
            if (currentChannel == null || !SessionHolder.isLogin(currentChannel)) {
                OffLineMessage offLineMessage = new OffLineMessage();
                offLineMessage.setFromUserId(SessionHolder.getCurrentUser(ctx.channel()));
                offLineMessage.setGroupName(requestPacket.getGroupName());
                offLineMessage.setMessage(requestPacket.getMessage());
                offLineMessage.setMessageReceivedTime(requestPacket.getMessageReceivedTime());
                offLineMessage.setSource(OffLineMessageSource.GROUP);
                // store off-line group message
                messageStore.storeMessage(user, offLineMessage);
            } else {
                GroupMessageResponsePacket responsePacket = new GroupMessageResponsePacket();
                responsePacket.setGroupName(requestPacket.getGroupName());
                responsePacket.setMessage(requestPacket.getMessage());
                responsePacket.setMessageReceivedTime(requestPacket.getMessageReceivedTime());
                responsePacket.setSender(SessionHolder.getCurrentUser(ctx.channel()));
                currentChannel.writeAndFlush(responsePacket);
            }
        });
    }
}
