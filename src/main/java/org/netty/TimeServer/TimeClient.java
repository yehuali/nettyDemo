package org.netty.TimeServer;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class TimeClient{
    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
       try{
           Bootstrap b = new Bootstrap();
           b.group(eventLoopGroup)
                   .channel(NioSocketChannel.class)
                   .option(ChannelOption.TCP_NODELAY,true)
                   .handler(new ChannelInitializer<SocketChannel>() {
                       @Override
                       protected void initChannel(SocketChannel socketChannel) throws Exception {
                           socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                           socketChannel.pipeline().addLast(new StringDecoder());
                           socketChannel.pipeline().addLast(new TimeClientHandler());
                       }
                   });
           ChannelFuture future =  b.connect("127.0.0.1",8088).sync();
           future.channel().closeFuture().sync();
       }finally {
           eventLoopGroup.shutdownGracefully();
       }


    }
}
