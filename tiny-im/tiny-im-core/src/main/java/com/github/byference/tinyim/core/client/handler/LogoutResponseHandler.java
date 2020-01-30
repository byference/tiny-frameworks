package com.github.byference.tinyim.core.client.handler;

import com.github.byference.tinyim.core.protocol.response.LogoutResponsePacket;
import com.github.byference.tinyim.core.util.SessionHolder;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * LogoutResponseHandler
 *
 * @author byference
 * @since 2020-01-23
 */
@ChannelHandler.Sharable
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {

    public static LogoutResponseHandler INSTANCE = new LogoutResponseHandler();

    private LogoutResponseHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket msg) throws Exception {

        System.out.println("注销成功");
        String currentUser = SessionHolder.getCurrentUser(ctx.channel());
        SessionHolder.unBind(currentUser, ctx.channel());
    }
}
