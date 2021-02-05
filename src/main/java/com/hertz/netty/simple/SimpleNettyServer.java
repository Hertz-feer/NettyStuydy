package com.hertz.netty.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author by Hertz
 * @Classname SimpleNettyServer
 * @Description netty简单服务、
 * @Date 2020/8/28 11:00
 */
public class SimpleNettyServer {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup boosGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(4);

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(boosGroup, workerGroup)
                    //设置boosGroup的通道
                    .channel(NioServerSocketChannel.class)
                    //设置boosGroup连接数
                    .option(ChannelOption.SO_BACKLOG, 128)
                    //设置workGroup规则
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    //设置workGroup执行方法
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyServerHandler());
                        }
                    });

            ChannelFuture cf = bootstrap.bind(6399).sync();

            cf.channel().closeFuture().sync();

        } finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
