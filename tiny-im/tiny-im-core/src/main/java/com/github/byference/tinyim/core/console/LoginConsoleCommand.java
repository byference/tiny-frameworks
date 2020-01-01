package com.github.byference.tinyim.core.console;

import com.github.byference.tinyim.core.protocol.request.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * LoginConsoleCommand
 *
 * @author byference
 * @since 2020-01-01
 */
public class LoginConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {

        System.out.println("请输入用户名:");
        final String username = scanner.nextLine();
        LoginRequestPacket packet = new LoginRequestPacket();
        packet.setUsername(username);
        packet.setPassword("--");
        channel.writeAndFlush(packet);
    }
}
