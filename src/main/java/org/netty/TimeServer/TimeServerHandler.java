package org.netty.TimeServer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.Date;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf buf = (ByteBuf)msg;
//        byte[] bytes = new byte[buf.readableBytes()];
//        buf.readBytes(bytes);
//        String request = new String(bytes,"UTF-8");
//        System.out.println("the time server receive order:"+request);
//        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(request) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
//        ByteBuf response = Unpooled.copiedBuffer(currentTime.getBytes());
//        ctx.write(response);

//        ByteBuf buf = (ByteBuf)msg;
//        String request = buf.toString(CharsetUtil.UTF_8);
//        String body = request.substring(0,request.length()  - System.getProperty("line.separator").length());
        String request = (String)msg;
        System.out.println("the time server receive order:"+request);
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(request) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
        currentTime = currentTime + System.getProperty("line.separator");
        ctx.write(Unpooled.copiedBuffer(currentTime,CharsetUtil.UTF_8));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        ctx.flush();
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
      cause.printStackTrace();
      ctx.close();
    }
}
