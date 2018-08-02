package org.netty.proxy.handler;

import io.netty.channel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProxyBackendHandler extends SimpleChannelInboundHandler<byte[]> {
    private static final Logger logger = LoggerFactory.getLogger(ProxyBackendHandler.class);

    private Channel inboundChannel;
    private ProxyFrontedHandler proxyFrontedHandler;
    private String host;
    private int port;

    public ProxyBackendHandler(Channel inboundChannel, ProxyFrontedHandler proxyFrontendHandler, String host,
                               int port) {
        this.inboundChannel = inboundChannel;
        this.proxyFrontedHandler = proxyFrontendHandler;
        this.host = host;
        this.port = port;
    }

    //当和目标服务器的通道连接建立时
  @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("服务器地址："+ctx.channel().remoteAddress());
    }


    //msg是从目标服务器返回的消息
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, byte[] msg) throws Exception {
        logger.info("服务器返回消息");
        /**
         * 接收目标服务器发送的数据并打印，然后把数据写入代理服务器和客户端的通道里
         */
        inboundChannel.writeAndFlush(msg).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if(!future.isSuccess()){
                    future.channel().close();
                }
            }
        });
    }
}
