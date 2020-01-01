package com.github.byference.tinyim.core.server.handler;

import com.github.byference.tinyim.core.protocol.request.LoginRequestPacket;
import com.github.byference.tinyim.core.protocol.response.LoginResponsePacket;
import com.github.byference.tinyim.core.util.SessionHolder;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * LoginRequestHandler
 *
 * @author byference
 * @since 2020-01-01
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket packet) throws Exception {

        // do login
        final String username = packet.getUsername();
        final String token = generateToken();
        SessionHolder.bind(token, ctx.channel());
        System.out.printf("用户[ %s ] 登陆成功\n", username);

        // response
        LoginResponsePacket responsePacket = new LoginResponsePacket();
        responsePacket.setUserId(UUID.randomUUID().toString());
        responsePacket.setUsername(username);
        responsePacket.setVersion(packet.getVersion());
        responsePacket.setToken(token);
        ctx.channel().writeAndFlush(responsePacket);
    }

    private String generateToken() {
        return "bearer " + UUID.randomUUID().toString();
    }
}
