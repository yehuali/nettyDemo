package org.netty.proxy.handler;

import io.netty.channel.ChannelHandlerContext;

public interface ChannelListener {
    public void inActive(ChannelHandlerContext ctx);

    public void active(ChannelHandlerContext ctx);
}
