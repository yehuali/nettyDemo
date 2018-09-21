package org.imooc.netty;

public  abstract class AbstractBootstrap {
    private ReflectiveChannelFactory channelFactory;

    private volatile ChannelHandler handler;

    public void bind(){
        Channel channel = channelFactory.newChannel();
    }

    public AbstractBootstrap channel(Class<? extends Channel> channelClass){
        if (channelClass == null) {
            throw new NullPointerException("channelClass");
        }
        channelFactory = new ReflectiveChannelFactory(channelClass);
        return this;
    }

    abstract void init(Channel channel) throws Exception;

    final ChannelHandler handler() {
        return handler;
    }

    public AbstractBootstrap handler(ChannelHandler handler) {
        if (handler == null) {
            throw new NullPointerException("handler");
        }
        this.handler = handler;
        return  this;
    }
}
