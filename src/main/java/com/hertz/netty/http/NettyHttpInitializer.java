package com.hertz.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author Hertz
 * @Classname NettyHttpInitializer
 * @Description 初始化器
 * @date 2020/8/30 19:05
 */
public class NettyHttpInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast("HertzHttpCode",new HttpServerCodec());
        ch.pipeline().addLast("HertzHttpHandler",new NettyHttpHandler());
    }
}
