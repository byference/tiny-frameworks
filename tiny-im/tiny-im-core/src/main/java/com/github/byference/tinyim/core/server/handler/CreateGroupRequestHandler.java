package com.github.byference.tinyim.core.server.handler;

import com.github.byference.tinyim.core.protocol.request.CreateGroupRequestPacket;
import com.github.byference.tinyim.core.protocol.response.CreateGroupResponsePacket;
import com.github.byference.tinyim.core.util.SessionHolder;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.commons.lang3.StringUtils;

/**
 * CreateGroupRequestHandler
 *
 * @author byference
 * @since 2020-01-26
 */
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket packet) {

        CreateGroupResponsePacket responsePacket = new CreateGroupResponsePacket();
        responsePacket.setSuccess(false);
        String groupName = packet.getGroupName();
        if (StringUtils.isBlank(groupName)) {
            responsePacket.setMessage("群名称不能为空");
        } else if (SessionHolder.containsGroupName(groupName)) {
            responsePacket.setMessage("群名称已被使用");
        } else {
            SessionHolder.createGroup(groupName, ctx);
            String success = "群名称为 [ " + groupName + " ]";
            System.out.println("群创建成功: " + success);
            responsePacket.setMessage(success);
            responsePacket.setSuccess(true);
        }
        ctx.channel().writeAndFlush(responsePacket);
    }
}
