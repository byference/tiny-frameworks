package com.github.byference.tinyim.core.util;

import com.github.byference.tinyim.core.constant.DefaultNettyConst;
import com.github.byference.tinyim.core.server.model.GroupMetadata;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SessionHolder
 *
 * @author byference
 * @since 2020-01-01
 */
public class SessionHolder {

    private static Map<String, Channel> sessionHolder = new ConcurrentHashMap<>();

    private static Map<String, ChannelGroup> groupHolder = new ConcurrentHashMap<>();

    private static Map<String, GroupMetadata> groupMetadata = new ConcurrentHashMap<>();

    private SessionHolder() {}

    public static Set<String> getGroupMembers(String groupName) {
        return new HashSet<>(groupMetadata.get(groupName).getGroupMembers());
    }

    public static ChannelGroup getGroup(String groupName) {
        return groupHolder.get(groupName);
    }

    public static void joinGroup(String groupName, List<String> users) {
        ChannelGroup channels = groupHolder.get(groupName);
        users.stream().distinct()
                .peek(groupMetadata.get(groupName).getGroupMembers()::add)
                .map(sessionHolder::get)
                .forEach(channels::add);
    }

    public static void createGroup(String groupName, ChannelHandlerContext ctx) {
        // create channelGroup
        DefaultChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());
        channelGroup.add(ctx.channel());
        groupHolder.put(groupName, channelGroup);

        // add channelGroup metadata
        GroupMetadata groupMetadata = new GroupMetadata();
        String currentUser = getCurrentUser(ctx.channel());
        groupMetadata.setGroupHolder(currentUser);
        Set<String> groupMembers = new HashSet<>();
        groupMembers.add(currentUser);
        groupMetadata.setGroupMembers(groupMembers);
        SessionHolder.groupMetadata.put(groupName, groupMetadata);
    }

    public static boolean containsGroupName(String groupName) {
        return StringUtils.isNotBlank(groupName) && groupHolder.containsKey(groupName);
    }

    public static Channel getChannel(String identify) {
        return sessionHolder.get(identify);
    }

    public static String getCurrentUser(Channel channel) {
        return channel.attr(DefaultNettyConst.TOKEN).get();
    }

    public static void bind(String identify, Channel channel) {
        sessionHolder.put(identify, channel);
        channel.attr(DefaultNettyConst.TOKEN).set(identify);
    }

    public static void unBind(String identify, Channel channel) {
        sessionHolder.remove(identify);
        channel.attr(DefaultNettyConst.TOKEN).set(null);
    }

    public static boolean isLogin(Channel channel) {
        return channel.attr(DefaultNettyConst.TOKEN).get() != null;
    }
}
