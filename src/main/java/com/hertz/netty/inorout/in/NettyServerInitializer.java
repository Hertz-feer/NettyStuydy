package com.hertz.netty.inorout.in;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;

/**
 * @author Hertz
 */
public class
NettyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new LineBasedFrameDecoder(2048));
        pipeline.addLast(new NettyServerByteToLongDecoder());
        //pipeline.addLast(new ReplayingByteToLongDecoder());
        pipeline.addLast(new NettyServerLongHandler());

    }
}
