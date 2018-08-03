package org.netty.proxy.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.socksx.v5.Socks5InitialRequestDecoder;
import io.netty.handler.codec.socksx.v5.Socks5ServerEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.netty.proxy.codec.FrontedEncode;
import org.netty.proxy.codec.FrontendDecode;
import org.netty.proxy.handler.ProxyChannelTrafficShapingHandler;
import org.netty.proxy.handler.ProxyFrontedHandler;
import org.netty.proxy.handler.ProxyIdleHandler;
import org.netty.proxy.log.ProxyFlowLog4j;
import org.netty.proxy.utils.ApplicationContextUtil;
import org.springframework.stereotype.Component;

@Component("frontendPipeline")
public class FrontendPipeline extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //流量统计
        pipeline.addLast(ProxyChannelTrafficShapingHandler.PROXY_TRAFFIC,new ProxyChannelTrafficShapingHandler(3000,new ProxyFlowLog4j(),null));
        //channel超时处理
        pipeline.addLast(new IdleStateHandler(3,30,0));
        pipeline.addLast(new ProxyIdleHandler());

        pipeline.addLast(Socks5ServerEncoder.DEFAULT);
        pipeline.addLast(new Socks5InitialRequestDecoder());


        //注册handler
//        pipeline.addLast(ApplicationContextUtil.getBean(FrontendDecode.class));
//        pipeline.addLast(ApplicationContextUtil.getBean(FrontedEncode.class));
        pipeline.addLast(ApplicationContextUtil.getBean(ProxyFrontedHandler.class));
    }
}
