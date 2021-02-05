package com.hertz.netty.groupchat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

/**
 * @author Hertz
 */
public class NettyGroupChatClient {

    private static final String HOST = "127.0.0.1";

    private static final int PORT = 6369;

    public void run() throws InterruptedException {

        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new NettyGroupClientInitializer());

            ChannelFuture future = bootstrap.connect(HOST, PORT).sync();

            Channel channel = future.channel();

            System.out.println("-------------"+channel.localAddress()+"------------------");

            Scanner sc = new Scanner(System.in);
            while (sc.hasNextLine()){
                String msg = sc.nextLine();
                channel.writeAndFlush(msg+"\r\n");
            }
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new NettyGroupChatClient().run();
    }
}
