package org.netty.Nio;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class ConnectHandler extends ChannelInboundHandlerAdapter {
    //当一个新的连接已经被建立时，channelActive（ChannelHandlerContext）将会被调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client "+ctx.channel().remoteAddress() + " connected");
    }

    /**
     * 注册一个ChannelFutureListener到对connect()方法的调用所返回的ChannelFuture上
     * 当该监听器被通知连接已经建立的时候，检查对应的状态
     *   1.操作成功，数据写到该Channel
     *   2.否则从ChannelFuture中检索对应的Throwable
     *
     */
    @Test
    public void callBackTest(){
//        Channel channel = ...;
//        //异步地连接到远程节点
//        ChannelFuture future = channel.connect(new InetSocketAddress("192.168.0.1",25));
//        //注册一个ChannelFutureListener，以便在操作完成时获得通知
//        future.addListener(new ChannelFutureListener() {
//            @Override
//            public void operationComplete(ChannelFuture channelFuture) throws Exception {
//                if(future.isSuccess()){
//                    //创建一个ByteBuf以持有数据
//                    ByteBuf buffer = Unpooled.copiedBuffer("Hello", Charset.defaultCharset());
//                    //将数据异步地发送到远程节点。返回一个ChannelFuture
//                    ChannelFuture wf = future.channel().writeAndFlush(buffer);
//                    ...
//                }else {
//                    Throwable cause = future.cause();
//                    cause.printStackTrace();
//                }
//            }
//        });
    }
}
