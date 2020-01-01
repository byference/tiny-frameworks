package com.github.byference.tinyim.core.console;

import io.netty.channel.Channel;
import java.util.Scanner;

/**
 * ConsoleCommand
 *
 * @author byference
 * @since 2020-01-01
 */
@FunctionalInterface
public interface ConsoleCommand {

    void exec(Scanner scanner, Channel channel);
}
