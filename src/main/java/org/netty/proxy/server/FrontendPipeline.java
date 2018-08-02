package org.netty.proxy.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.netty.proxy.codec.FrontedEncode;
import org.netty.proxy.codec.FrontendDecode;
import org.netty.proxy.handler.ProxyFrontedHandler;
import org.netty.proxy.utils.ApplicationContextUtil;
import org.springframework.stereotype.Component;

@Component("frontendPipeline")
public class FrontendPipeline extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //注册handler
        pipeline.addLast(ApplicationContextUtil.getBean(FrontendDecode.class));
        pipeline.addLast(ApplicationContextUtil.getBean(FrontedEncode.class));
        pipeline.addLast(ApplicationContextUtil.getBean(ProxyFrontedHandler.class));
    }
}
