package com.hertz.netty.inorout.out;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author Hertz
 */
public class NettyClientLongToByteEncoder extends MessageToByteEncoder<Long> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {
        System.out.println("This is NettyClientLongToByteEncoder method encode :"+msg);
//        out.writeBytes(Unpooled.copyLong(msg));
        out.writeLong(msg);
    }
}
