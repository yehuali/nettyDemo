package org.netty.web;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    /**
     *  当被通知Channle是活跃的时候，发送一条消息
     *  将在一个连接建立时被调用，确保数据将会被尽可能写入服务器
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!",CharsetUtil.UTF_8));
    }

    /**
     * 记录已接受消息的转储
     * 每当接受数据时，都会调用此方法
     *   1.服务器发送的消息可能会被分块接收
     *   2.作为一个面向流的协议，TCP保证了字节数组将会按照服务器发送他们的顺序被接收
     * 当该方法返回时，SimleChannelInboundHandler负责释放指向保存该消息的ByteBuf的内存引用
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf in) throws Exception {
        System.out.println("Client received: "+in.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
