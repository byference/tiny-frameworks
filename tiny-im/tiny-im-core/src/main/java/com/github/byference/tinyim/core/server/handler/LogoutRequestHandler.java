package com.github.byference.tinyim.core.server.handler;

import com.github.byference.tinyim.core.protocol.request.LogoutRequestPacket;
import com.github.byference.tinyim.core.protocol.response.LogoutResponsePacket;
import com.github.byference.tinyim.core.util.SessionHolder;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * LogoutRequestHandler
 *
 * @author byference
 * @since 2020-01-23
 */
@ChannelHandler.Sharable
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

    public static LogoutRequestHandler INSTANCE = new LogoutRequestHandler();

    private LogoutRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket packet) {

        LogoutResponsePacket responsePacket = new LogoutResponsePacket();
        try {
            String currentUser = SessionHolder.getCurrentUser(ctx.channel());
            SessionHolder.unBind(currentUser, ctx.channel());
            responsePacket.setSuccess(true);
            System.out.printf("用户 [ %s ] 注销成功.\n", currentUser);
        } catch (Exception e) {
            e.printStackTrace();
            responsePacket.setSuccess(false);
            responsePacket.setMessage(e.getMessage());
        }
        ctx.channel().writeAndFlush(responsePacket);
    }
}
