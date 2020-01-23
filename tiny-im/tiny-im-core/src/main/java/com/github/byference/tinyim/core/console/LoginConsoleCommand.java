package com.github.byference.tinyim.core.console;

import com.github.byference.tinyim.core.protocol.request.LoginRequestPacket;
import com.github.byference.tinyim.core.util.SessionHolder;
import io.netty.channel.Channel;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * LoginConsoleCommand
 *
 * @author byference
 * @since 2020-01-01
 */
public class LoginConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {

        if (SessionHolder.isLogin(channel)) {
            System.out.println("当前用户已登陆...");
            return;
        }

        System.out.println("当前用户未登录 - 请输入用户名:");
        final String username = scanner.nextLine();
        if (StringUtils.isNotBlank(username)) {
            LoginRequestPacket packet = new LoginRequestPacket();
            packet.setUsername(username);
            packet.setPassword("--");
            channel.writeAndFlush(packet);
            waitForResponse();
        }
    }

    public void waitForResponse() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
