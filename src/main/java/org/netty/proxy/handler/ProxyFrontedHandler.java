package org.netty.proxy.handler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.ChannelGroupFuture;
import io.netty.channel.group.ChannelGroupFutureListener;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.netty.proxy.server.BackendPipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.SocketAddress;
import java.util.concurrent.TimeUnit;

@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ProxyFrontedHandler extends SimpleChannelInboundHandler<byte[]> {

    private static final Logger log = LoggerFactory.getLogger(ProxyFrontedHandler.class);

    //代理服务器和目标服务器之间的通道（从代理服务器出去所以是outbound过境）
    private volatile ChannelGroup allChannels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private volatile boolean frontendConnectStatus = false;

    /**
     * 当客户端和代理服务器建立通道连接时，调用此方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        frontendConnectStatus = true;

        SocketAddress clientAddress = ctx.channel().remoteAddress();
        log.info("客户端地址："+clientAddress);

        /**
         * 客户端和代理服务器的连接通道 入境的通道
         */
        Channel inboundChannel = ctx.channel();
        //遍历目标服务器列表，创建引导类
        createBootstrap(inboundChannel,"192.168.1.239",8080);


    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, byte[] msg) throws Exception {
        log.info("客户端消息");
        allChannels.writeAndFlush(msg).addListener(new ChannelGroupFutureListener() {
            @Override
            public void operationComplete(ChannelGroupFuture channelFutures) throws Exception {
                //防止出现发送不成功造成的永久不读取消息的错误
                ctx.channel().read();
            }
        });
    }

    public void createBootstrap(final Channel inboundChannel,final String host,final int port){
      try {
          Bootstrap bootstrap = new Bootstrap();

          bootstrap.group(inboundChannel.eventLoop());
          bootstrap.channel(NioSocketChannel.class);

          bootstrap.handler(new BackendPipeline(inboundChannel, ProxyFrontedHandler.this, host, port));

          ChannelFuture f = bootstrap.connect(host, port);
          f.addListener(new ChannelFutureListener() {
              @Override
              public void operationComplete(ChannelFuture future) throws Exception {
                  if (future.isSuccess()) {
                      allChannels.add(future.channel());
                  } else {
                      if (inboundChannel.isActive()) {
                          log.info("Reconnect");
                          final EventLoop loop = future.channel().eventLoop();
                          loop.schedule(new Runnable() {
                              @Override
                              public void run() {
                                  ProxyFrontedHandler.this.createBootstrap(inboundChannel, host, port);
                              }
                          }, 1000, TimeUnit.MILLISECONDS);
                      } else {
                          log.info("noActive");
                      }
                  }
                  inboundChannel.read();
              }
          });
      }catch (Exception e){

      }
    }
}
