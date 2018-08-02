package org.netty.proxy.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.netty.proxy.codec.BackendDecode;
import org.netty.proxy.codec.BackendEncode;
import org.netty.proxy.handler.ProxyBackendHandler;
import org.netty.proxy.handler.ProxyFrontedHandler;

public class BackendPipeline extends ChannelInitializer<SocketChannel> {
    private Channel inboundChannel;
    private ProxyFrontedHandler proxyFrontedHandler;
    private String host;
    private int port;

    public BackendPipeline(Channel inboundChannel, ProxyFrontedHandler proxyFrontedHandler, String host, int port) {
        this.inboundChannel = inboundChannel;
        this.proxyFrontedHandler = proxyFrontedHandler;
        this.host = host;
        this.port = port;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //注册handler
        pipeline.addLast(new BackendDecode());
        pipeline.addLast(new BackendEncode());
        pipeline.addLast(new ProxyBackendHandler(inboundChannel,proxyFrontedHandler,host,port));
    }
}
