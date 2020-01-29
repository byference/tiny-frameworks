package com.github.byference.tinyim.core.client.handler;

import com.github.byference.tinyim.core.protocol.request.HeartBeatRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.util.concurrent.TimeUnit;
import static com.github.byference.tinyim.core.constant.DefaultNettyConst.HEARTBEAT_INTERVAL;

/**
 * HeartBeatTimerHandler
 *
 * @author byference
 * @since 2020-01-29
 */
public class HeartBeatTimerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        scheduleSendHeartBeat(ctx);
        super.channelActive(ctx);
    }

    private void scheduleSendHeartBeat(ChannelHandlerContext ctx) {
        ctx.executor().schedule(() -> {
            if (ctx.channel().isActive()) {
                ctx.channel().writeAndFlush(new HeartBeatRequestPacket());
                scheduleSendHeartBeat(ctx);
            }
        }, HEARTBEAT_INTERVAL, TimeUnit.SECONDS);
    }
}
