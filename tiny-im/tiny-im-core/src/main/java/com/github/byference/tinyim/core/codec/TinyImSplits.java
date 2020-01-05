package com.github.byference.tinyim.core.codec;

import com.github.byference.tinyim.core.constant.DefaultNettyConst;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * TinyImSplits
 *
 * @author byference
 * @since 2020-01-05
 */
public class TinyImSplits extends LengthFieldBasedFrameDecoder {

    /**
     * magic number(4) + version(1) + command(1)
     */
    private static final int LENGTH_FIELD_OFFSET = 6;

    /**
     * length of data-length
     */
    private static final int LENGTH_FIELD_LENGTH = 4;

    public TinyImSplits() {
        super(Integer.MAX_VALUE, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {

        if (in.getInt(in.readerIndex()) != DefaultNettyConst.MAGIC_NUMBER) {
            ctx.channel().close();
            return null;
        }
        return super.decode(ctx, in);
    }
}
