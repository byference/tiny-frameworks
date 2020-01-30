package com.github.byference.tinyim.core.codec;

import com.alibaba.fastjson.JSON;
import com.github.byference.tinyim.core.constant.DefaultNettyConst;
import com.github.byference.tinyim.core.protocol.Command;
import com.github.byference.tinyim.core.protocol.Packet;
import com.github.byference.tinyim.core.protocol.request.*;
import com.github.byference.tinyim.core.protocol.response.*;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PacketCodecHandler
 *
 * @author byference
 * @since 2020-01-30
 */
@ChannelHandler.Sharable
public class PacketCodecHandler extends MessageToMessageCodec<ByteBuf, Packet> {

    public static PacketCodecHandler INSTANCE = new PacketCodecHandler();

    private final Map<Byte, Class<? extends Packet>> packetTypeMapper;

    private PacketCodecHandler() {
        packetTypeMapper = new HashMap<>();
        packetTypeMapper.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMapper.put(Command.LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMapper.put(Command.MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMapper.put(Command.MESSAGE_RESPONSE, MessageResponsePacket.class);
        packetTypeMapper.put(Command.LOGOUT_REQUEST, LogoutRequestPacket.class);
        packetTypeMapper.put(Command.LOGOUT_RESPONSE, LogoutResponsePacket.class);
        packetTypeMapper.put(Command.CREATE_GROUP_REQUEST, CreateGroupRequestPacket.class);
        packetTypeMapper.put(Command.CREATE_GROUP_RESPONSE, CreateGroupResponsePacket.class);
        packetTypeMapper.put(Command.JOIN_GROUP_REQUEST, JoinGroupRequestPacket.class);
        packetTypeMapper.put(Command.JOIN_GROUP_RESPONSE, JoinGroupResponsePacket.class);
        packetTypeMapper.put(Command.GROUP_NOTIFICATION_RESPONSE, GroupNotificationResponsePacket.class);
        packetTypeMapper.put(Command.GROUP_MESSAGE_REQUEST, GroupMessageRequestPacket.class);
        packetTypeMapper.put(Command.GROUP_MESSAGE_RESPONSE, GroupMessageResponsePacket.class);
        packetTypeMapper.put(Command.HEARTBEAT_REQUEST, HeartBeatRequestPacket.class);
        packetTypeMapper.put(Command.HEARTBEAT_RESPONSE, HeartBeatResponsePacket.class);
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, List<Object> out) throws Exception {
        ByteBuf byteBuf = ctx.channel().alloc().ioBuffer();
        byte[] data = JSON.toJSONBytes(packet);
        byteBuf.writeInt(DefaultNettyConst.MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(data.length);
        byteBuf.writeBytes(data);
        out.add(byteBuf);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // skip magic number
        in.skipBytes(4);
        // skip version
        in.skipBytes(1);
        byte command = in.readByte();
        int length = in.readInt();
        byte[] bytes = new byte[length];
        in.readBytes(bytes);
        Class<? extends Packet> clazz = packetTypeMapper.get(command);
        out.add(JSON.parseObject(bytes, clazz));
    }
}
