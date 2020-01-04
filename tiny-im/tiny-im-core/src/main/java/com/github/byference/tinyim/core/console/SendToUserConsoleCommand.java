package com.github.byference.tinyim.core.console;

import com.github.byference.tinyim.core.protocol.request.MessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * SendToUserConsoleCommand
 *
 * @author byference
 * @since 2020-01-03
 */
public class SendToUserConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入发送内容 (格式: 用户名 消息):");
        String toUserId = scanner.next();
        String message = scanner.next();
        MessageRequestPacket packet = new MessageRequestPacket();
        packet.setToUserId(toUserId);
        packet.setMessage(message);
        channel.writeAndFlush(packet);
    }
}
