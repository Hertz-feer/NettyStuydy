package com.hertz.netty.groupchat;

import cn.hutool.core.date.DateUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Date;

/**
 * @author by Hertz
 * @Classname NettyGroupServerHandler
 * @Description netty服务器业务处理器
 * @Date 2020/9/4 16:33
 */
public class NettyGroupServerHandler extends SimpleChannelInboundHandler<String> {

    private static final ChannelGroup CHANNEL_GROUP = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        CHANNEL_GROUP.add(channel);
        channel.writeAndFlush(DateUtil.formatDateTime(new Date()) + "：[客户端]" + channel.remoteAddress() + "上线！\n");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channel.writeAndFlush(DateUtil.formatDateTime(new Date()) + "：[客户端]" + channel.remoteAddress() + "下线！\n");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("用户：" + channel.remoteAddress() + "上线了！");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("用户：" + channel.remoteAddress() + "下线了！");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();

        CHANNEL_GROUP.forEach(ch -> {
            if (!ch.equals(channel)) {
                ch.writeAndFlush("[" + ch.remoteAddress() + "]：" + msg);
            } else {
                ch.writeAndFlush("[自己]：" + msg);
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().close();
    }
}
