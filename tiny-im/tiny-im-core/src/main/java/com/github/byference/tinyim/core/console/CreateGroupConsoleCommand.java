package com.github.byference.tinyim.core.console;

import com.github.byference.tinyim.core.protocol.request.CreateGroupRequestPacket;
import io.netty.channel.Channel;
import java.util.Scanner;

/**
 * CreateGroupConsoleCommand
 *
 * @author byference
 * @since 2020-01-26
 */
public class CreateGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {

        System.out.println("请输入群名称:");
        String groupName = scanner.nextLine();

        CreateGroupRequestPacket packet = new CreateGroupRequestPacket();
        packet.setGroupName(groupName);
        channel.writeAndFlush(packet);
    }
}
