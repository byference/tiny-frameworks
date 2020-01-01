package com.github.byference.tinyim.core.util;

import com.github.byference.tinyim.core.constant.DefaultNettyConst;
import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SessionHolder
 *
 * @author byference
 * @since 2020-01-01
 */
public class SessionHolder {

    public static Map<String, Channel> HOLDER = new ConcurrentHashMap<>();

    private SessionHolder() {}


    public static void bind(String token, Channel channel) {
        HOLDER.put(token, channel);
        channel.attr(DefaultNettyConst.TOKEN).set(token);
    }

}
