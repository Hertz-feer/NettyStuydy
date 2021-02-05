package com.hertz.netty.hearbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author Hertz
 */
public class NettyIdleStateHandler extends SimpleChannelInboundHandler<String> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        String status = null;
        if (evt instanceof IdleStateEvent){
            IdleStateEvent stateEvent = (IdleStateEvent) evt;
            switch (stateEvent.state()){
                case ALL_IDLE:
                    status = "读写空闲";
                    break;
                case READER_IDLE:
                    status = "读空闲";
                    break;
                case WRITER_IDLE:
                    status = "写空闲";
                    break;
                default:
            }

            System.out.println("当前处于："+status);
        }
    }
}
