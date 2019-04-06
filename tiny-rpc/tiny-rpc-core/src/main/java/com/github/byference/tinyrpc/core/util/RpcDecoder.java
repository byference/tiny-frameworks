package com.github.byference.tinyrpc.core.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * decoder
 *
 * @author byference
 * @since 2019/04/01
 */
public class RpcDecoder extends ByteToMessageDecoder {

    private Class clazz;

    public RpcDecoder(Class clazz) {
        this.clazz = clazz;
    }


    @Override
    protected void decode(ChannelHandlerContext context, ByteBuf in, List<Object> out) throws Exception {

        int size = in.readableBytes();
        if (size < 4) {
            return;
        }
        byte[] bytes = new byte[size];
        in.readBytes(bytes);
        Object object = SerializationUtil.deserialize(bytes, clazz);
        out.add(object);
        context.flush();
    }

}
