package org.imooc.netty;

public class ChannelPipeline {

    public final ChannelPipeline addLast(ChannelHandler... handlers) {
        return this;
    }
}
