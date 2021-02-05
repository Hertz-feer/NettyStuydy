package com.hertz.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * @author Hertz
 * @date 2020/9/2 20:42
 */
public class NettyBuffer02 {

    public static void main(String[] args) {

        ByteBuf byteBuf = Unpooled.copiedBuffer("This is copiedBuffer", CharsetUtil.UTF_8);


        System.out.println("读取索引位置：" + byteBuf.readerIndex());
        System.out.println("写入索引位置：" + byteBuf.writerIndex());
        System.out.println("最大容量：" + byteBuf.capacity());
        System.out.println("第一个可读字节：" + byteBuf.arrayOffset());
    }
}
