package com.github.byference.tinyim.core.console;

import com.github.byference.tinyim.core.protocol.request.LogoutRequestPacket;
import com.github.byference.tinyim.core.util.SessionHolder;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * LogoutConsoleCommand
 *
 * @author byference
 * @since 2020-01-23
 */
public class LogoutConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {

        if (!SessionHolder.isLogin(channel)) {
            System.out.println("当前用户未登陆...");
            return;
        }
        LogoutRequestPacket logoutRequestPacket = new LogoutRequestPacket();
        channel.writeAndFlush(logoutRequestPacket);
    }
}
