package com.github.byference.tinyrpc.core.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * encoder
 *
 * @author byference
 * @since 2019/04/01
 */
public class RpcEncoder extends MessageToByteEncoder {

    private Class clazz;

    public RpcEncoder(Class clazz) {
        this.clazz = clazz;
    }


    @Override
    protected void encode(ChannelHandlerContext context, Object in, ByteBuf out) throws Exception {

        if (clazz.isInstance(in)) {
            byte[] bytes = SerializationUtil.serialize(in);
            out.writeBytes(bytes);
        }
    }

}
