package org.netty.web;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**ChannelHandler，一个接口族的父接口：实现负责接收并响应事件通知
 * 1.针对不同类型的事件来调用ChannleHandler
 * 2.通过实现或者扩展ChannelHandler来挂钩到事件的生命周期，并且提供其自定义的应用程序逻辑
 * 3.在架构上，ChannelHandler有助于保持业务逻辑与网络处理代码的分离
 */

@ChannelHandler.Sharable  //标示一个Chanel-Handler可以被多个Channel安全地共享
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 对于每个传入的消息都要调用
     *  write()操作是异步的，直到channleRead()方法返回后可能仍然没有完成
     *  ChannelInboundHandlerAdapter，在这个时间点不会释放消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf)msg;
        System.out.println("Server received: "+in.toString(CharsetUtil.UTF_8));
        //将接收到的消息写给发送者，而不冲刷出站消息
        ctx.write(in);
    }

    //通知ChannleInboundHandler最后一次对channelRead()的调用是当前批量读取中的最后一条消息
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将未决消息冲刷到远程节点，并且关闭该Channel
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
