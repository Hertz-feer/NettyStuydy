package com.hertz.netty.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author Hertz
 */
public class NettyWebSocketServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();

        //基于Http协议  所以需要解码器
        pipeline.addLast(new HttpServerCodec());
        //以块的方式写 
        pipeline.addLast(new ChunkedWriteHandler());
        //http协议通过 分段 传输数据 使用这个处理器 聚合请求
        pipeline.addLast(new HttpObjectAggregator(8192));
        pipeline.addLast(new WebSocketServerProtocolHandler("/hello"));
        pipeline.addLast(new NettyWebSocketServerHandler());

    }
    
}
