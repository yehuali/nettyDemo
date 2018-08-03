package org.netty.proxy.log;

import io.netty.channel.ChannelHandlerContext;

public interface ProxyFlowLog {
    public void log(ChannelHandlerContext ctx);
}
