package com.github.byference.tinyim.core.server.handler;

import com.github.byference.tinyim.core.message.InMemoryOffLineMessageStore;
import com.github.byference.tinyim.core.message.OffLineMessage;
import com.github.byference.tinyim.core.message.OffLineMessageStore;
import com.github.byference.tinyim.core.protocol.request.LoginRequestPacket;
import com.github.byference.tinyim.core.protocol.response.LoginResponsePacket;
import com.github.byference.tinyim.core.protocol.response.MessageResponsePacket;
import com.github.byference.tinyim.core.util.SessionHolder;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Comparator;
import java.util.List;
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
        SessionHolder.bind(username, ctx.channel());
        System.out.printf("用户[ %s ] 登陆成功\n", username);

        // response
        LoginResponsePacket responsePacket = new LoginResponsePacket();
        responsePacket.setUserId(UUID.randomUUID().toString());
        responsePacket.setUsername(username);
        responsePacket.setVersion(packet.getVersion());
        responsePacket.setToken(token);
        ctx.channel().writeAndFlush(responsePacket);

        // off-line message
        OffLineMessageStore messageStore = InMemoryOffLineMessageStore.getInstance();
        List<OffLineMessage> offLineMessages = messageStore.readMessage(username);
        offLineMessages.stream()
                .sorted(Comparator.comparingLong(offLineMessage -> offLineMessage.getMessageReceivedTime().getTime()))
                .map(offLineMessage -> {
                    MessageResponsePacket offLineResponsePacket = new MessageResponsePacket();
                    offLineResponsePacket.setFromUserId(offLineMessage.getFromUserId());
                    offLineResponsePacket.setFromUserName(offLineMessage.getFromUserId());
                    offLineResponsePacket.setMessage(offLineMessage.getContent());
                    offLineResponsePacket.setSource(offLineMessage.getSource());
                    return offLineResponsePacket;
                }).forEach(offLineResponsePacket -> ctx.channel().writeAndFlush(offLineResponsePacket));
        messageStore.removeMessage(username);
    }

    private String generateToken() {
        return "bearer " + UUID.randomUUID().toString();
    }
}
