package com.hertz.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @author Hertz
 * @date 2020/9/2 17:37
 */
public class NettyBuffer {

    public static void main(String[] args) {

        ByteBuf buffer = Unpooled.buffer(10);

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.writeByte(1);
        }

        for (int i = 0; i < buffer.capacity(); i++){
            System.out.println(buffer.readByte());
        }
    }
}
