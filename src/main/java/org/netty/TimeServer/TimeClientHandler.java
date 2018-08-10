package org.netty.TimeServer;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class TimeClientHandler extends SimpleChannelInboundHandler<String> {
    private int count;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
            String req = "QUERY TIME ORDER" + System.getProperty("line.separator");
            for(int i=0;i<100;i++){
                ctx.writeAndFlush(Unpooled.copiedBuffer(req,CharsetUtil.UTF_8));
            }


    }


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
//        byte[] bytes = new byte[byteBuf.readableBytes()];
//        byteBuf.readBytes(bytes);
//        String response = new String(bytes,"utf-8");
//        System.out.println("Now is"+response);
        System.out.println("Now is "+msg+";the count is " + ++count);

    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
