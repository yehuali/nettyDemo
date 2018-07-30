package org.netty.web;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class EchoServer {
    private final int port;
    public EchoServer(int port){
        this.port = port;
    }

    public static void main(String[] args) throws Exception{
//        if(args.length != 1){
//            System.err.println("Usage: "+EchoServer.class.getSimpleName() + " <port>");
//        }
//        int port = Integer.parseInt(args[0]);
//        new EchoServer(port).start();
        new EchoServer(8080).start();
    }

    public void start() throws Exception{
        final EchoServerHandler serverHandler = new EchoServerHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    .channel(NioServerSocketChannel.class) //指定所使用的NIO传输Channle
                    .localAddress(new InetSocketAddress(port)) //使用指定端口设置套接字地址
                    //一个新的连接被接受时，一个新的子Channle将会被创建
                    .childHandler(new ChannelInitializer<SocketChannel>() {  //添加一个EchoServerHandler到子Channel的ChannelPipeline
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            //EchoServerHandler被标注为@Shareable，所以我们可以总是使用同样的实例
                            ch.pipeline().addLast(serverHandler);
                        }
                    });
            ChannelFuture f = b.bind().sync();//异步地绑定服务器；调用sync()方法阻塞等到直到绑定完成
            f.channel().closeFuture().sync(); //获取Channle的CloseFuture，并且阻塞当前线程直到它完成
        }finally {
            group.shutdownGracefully().sync();//关闭EventLoopGroup，释放所有的资源
        }

    }
}
