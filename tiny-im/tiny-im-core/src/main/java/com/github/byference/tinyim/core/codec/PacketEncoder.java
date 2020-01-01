package com.github.byference.tinyim.core.codec;

import com.alibaba.fastjson.JSON;
import com.github.byference.tinyim.core.constant.DefaultNettyConst;
import com.github.byference.tinyim.core.protocol.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * PacketEncoder
 *
 * @author byference
 * @since 2020-01-01
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf out) {
        byte[] data = JSON.toJSONBytes(packet);
        out.writeInt(DefaultNettyConst.MAGIC_NUMBER);
        out.writeByte(packet.getVersion());
        out.writeByte(packet.getCommand());
        out.writeInt(data.length);
        out.writeBytes(data);
    }
}
