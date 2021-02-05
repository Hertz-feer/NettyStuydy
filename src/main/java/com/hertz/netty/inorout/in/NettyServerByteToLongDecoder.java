package com.hertz.netty.inorout.in;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author Hertz
 */
public class NettyServerByteToLongDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("this is decode method");
        if (in.readableBytes() >= 8) {
            out.add(in.readLong());
        }
    }
}
