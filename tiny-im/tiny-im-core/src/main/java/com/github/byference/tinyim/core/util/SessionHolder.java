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


    public static Channel getChannel(String identify) {
        return HOLDER.get(identify);
    }

    public static String getCurrentUser(Channel channel) {
        return channel.attr(DefaultNettyConst.TOKEN).get();
    }

    public static void bind(String identify, Channel channel) {
        HOLDER.put(identify, channel);
        channel.attr(DefaultNettyConst.TOKEN).set(identify);
    }

    public static boolean isLogin(Channel channel) {
        return channel.attr(DefaultNettyConst.TOKEN).get() != null;
    }
}
