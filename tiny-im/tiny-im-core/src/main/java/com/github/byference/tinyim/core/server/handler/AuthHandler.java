package com.github.byference.tinyim.core.server.handler;

import com.github.byference.tinyim.core.util.SessionHolder;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * AuthHandler
 *
 * @author byference
 * @since 2020-01-25
 */
@ChannelHandler.Sharable
public class AuthHandler extends ChannelInboundHandlerAdapter {

    public static AuthHandler INSTANCE = new AuthHandler();

    private AuthHandler() {}

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (SessionHolder.isLogin(ctx.channel())) {
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        } else {
            ctx.channel().close();
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        if (SessionHolder.isLogin(ctx.channel())) {
            System.out.println("当前连接已验证, AuthHandler自动移除...");
        } else {
            System.out.println("关闭非法连接...");
        }
    }
}
