package com.github.byference.tinyim.core.codec;

import com.alibaba.fastjson.JSON;
import com.github.byference.tinyim.core.protocol.Command;
import com.github.byference.tinyim.core.protocol.Packet;
import com.github.byference.tinyim.core.protocol.request.LoginRequestPacket;
import com.github.byference.tinyim.core.protocol.request.MessageRequestPacket;
import com.github.byference.tinyim.core.protocol.response.LoginResponsePacket;
import com.github.byference.tinyim.core.protocol.response.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PacketDecoder
 *
 * @author byference
 * @since 2020-01-01
 */
public class PacketDecoder extends ByteToMessageDecoder {

    private final Map<Byte, Class<? extends Packet>> packetTypeMapper;

    public PacketDecoder() {
        packetTypeMapper = new HashMap<>();
        packetTypeMapper.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMapper.put(Command.LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMapper.put(Command.MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMapper.put(Command.MESSAGE_RESPONSE, MessageResponsePacket.class);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {

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
