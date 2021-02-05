package com.hertz.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * @author Hertz
 * @date 2020/8/30 19:04
 */
public class NettyHttpHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        if (msg instanceof HttpRequest) {

            HttpRequest request = (HttpRequest) msg;
            URI uri = new URI(request.uri());

            if ("/favicon.ico".equals(uri.getPath())) {

                System.out.println("请求/favicon.ico不做任何处理; 执行这个任务的线程是：" + Thread.currentThread().getName()+"; pipeline的hash值为："+ctx.pipeline().hashCode());

            } else {

                ByteBuf buffer = Unpooled.copiedBuffer("你好呀，欢迎访问服务器！！", CharsetUtil.UTF_8);

                DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buffer);

                response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=UTF-8");

                response.headers().set(HttpHeaderNames.CONTENT_LENGTH, buffer.readableBytes());

                System.out.println("执行这个任务的线程是：" + Thread.currentThread().getName()+"; pipeline的hash值为："+ctx.pipeline().hashCode());

                ctx.writeAndFlush(response);
            }
        }
    }
}
