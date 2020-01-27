package com.github.byference.tinyim.core.console;

import com.github.byference.tinyim.core.protocol.request.JoinGroupRequestPacket;
import io.netty.channel.Channel;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * JoinGroupConsoleCommand
 *
 * @author byference
 * @since 2020-01-27
 */
public class JoinGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {

        System.out.println("请输入群名:");
        String groupName = scanner.nextLine();

        System.out.println("请输入拉入群聊用户名称（英文逗号分割）:");
        String users = scanner.nextLine();
        if (StringUtils.isBlank(users) || !users.contains(",")) {
            return;
        }

        List<String> userList = Arrays.stream(StringUtils.split(users, ",")).collect(Collectors.toList());
        JoinGroupRequestPacket joinGroupRequestPacket = new JoinGroupRequestPacket();
        joinGroupRequestPacket.setUserList(userList);
        joinGroupRequestPacket.setGroupName(groupName);
        channel.writeAndFlush(joinGroupRequestPacket);
    }

}
