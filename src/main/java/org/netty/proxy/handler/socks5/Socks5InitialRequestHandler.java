package org.netty.proxy.handler.socks5;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.socksx.v5.DefaultSocks5InitialRequest;
import org.netty.proxy.server.ProxyServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Socks5InitialRequestHandler extends SimpleChannelInboundHandler<DefaultSocks5InitialRequest> {
    private static final Logger logger = LoggerFactory.getLogger(Socks5InitialRequestHandler.class);

    private ProxyServer proxyServer;

    public Socks5InitialRequestHandler(ProxyServer proxyServer){
        this.proxyServer = proxyServer;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DefaultSocks5InitialRequest defaultSocks5InitialRequest) throws Exception {

    }
}
