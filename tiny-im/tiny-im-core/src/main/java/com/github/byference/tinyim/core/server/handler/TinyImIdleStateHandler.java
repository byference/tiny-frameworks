package com.github.byference.tinyim.core.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import java.util.concurrent.TimeUnit;
import static com.github.byference.tinyim.core.constant.DefaultNettyConst.READER_IDLE_TIME;

/**
 * TinyImIdleStateHandler
 *
 * @author byference
 * @since 2020-01-29
 */
public class TinyImIdleStateHandler extends IdleStateHandler {

    public TinyImIdleStateHandler() {
        super(READER_IDLE_TIME, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) {
        System.out.println(READER_IDLE_TIME + " 秒内未读到数据,关闭连接...");
        ctx.channel().close();
    }
}
