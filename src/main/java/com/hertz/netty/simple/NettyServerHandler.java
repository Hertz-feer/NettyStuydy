package com.hertz.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author by Hertz
 * @Classname NettyServerHandler
 * @Description Netty的业务处理器
 * @Date 2020/8/28 15:48
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf byteBuffer = (ByteBuf) msg;
        System.out.println("处理消息的线程是：" + Thread.currentThread().getName());
        System.out.println("客户端发来的消息是：" + byteBuffer.toString(CharsetUtil.UTF_8));
        System.out.println("客户端的地址是：" + ctx.channel().remoteAddress());
        ///这里是阻塞的方法
        // Thread.sleep(3000);
        // ctx.writeAndFlush(Unpooled.copiedBuffer("服务器的channelRead方法回复客户端：你好！！",CharsetUtil.UTF_8));
        ///异步方法
        ctx.channel().eventLoop().execute(() -> {
            try {
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName());
                ctx.writeAndFlush(Unpooled.copiedBuffer("服务器的channelRead的execute1方法回复客户端：你好！！",CharsetUtil.UTF_8));
            } catch (InterruptedException e) {
                System.out.println("执行失败了！！！");
            }
        });
        ctx.channel().eventLoop().execute(() -> {
            try {
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName());
                ctx.writeAndFlush(Unpooled.copiedBuffer("服务器的channelRead的execute2方法回复客户端：你好呀！！",CharsetUtil.UTF_8));
            } catch (InterruptedException e) {
                System.out.println("执行失败了！！！");
            }
        });
        ///定时任务
        ctx.channel().eventLoop().schedule(() -> {
            try {
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName());
                ctx.writeAndFlush(Unpooled.copiedBuffer("服务器的channelRead的schedule方法回复客户端：你好呀！！",CharsetUtil.UTF_8));
            } catch (InterruptedException e) {
                System.out.println("执行失败了！！！");
            }
        },3, TimeUnit.SECONDS);
        System.out.println("go on...");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelReadComplete处理消息的线程是：" + Thread.currentThread().getName());
        ctx.writeAndFlush(Unpooled.copiedBuffer("服务器的channelReadComplete方法回复客户端：你好！ 大傻吊", CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
