package com.github.byference.tinyim.core.console;

import com.github.byference.tinyim.core.protocol.request.GroupMessageRequestPacket;
import io.netty.channel.Channel;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * GroupMessageConsoleCommand
 *
 * @author byference
 * @since 2020-01-27
 */
public class GroupMessageConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入待发送内容 (格式: 群名称 消息内容):");
        String message = scanner.nextLine();
        final String[] messageArray = StringUtils.split(message, " ");
        GroupMessageRequestPacket requestPacket = new GroupMessageRequestPacket();
        requestPacket.setGroupName(messageArray[0]);
        requestPacket.setMessage(messageArray[1]);
        requestPacket.setMessageReceivedTime(LocalDateTime.now());
        channel.writeAndFlush(requestPacket);
    }
}
