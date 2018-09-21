package org.imooc.netty;

import io.netty.channel.ChannelException;

import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.spi.SelectorProvider;

public class Channel {
    private final NioServerSocketChannelConfig config;
    private static final SelectorProvider DEFAULT_SELECTOR_PROVIDER = SelectorProvider.provider();
    private final SelectableChannel ch;

    private final int id;//channel的唯一标识
    private final String unsafe;
    private final ChannelPipeline pipeline;
    private volatile EventLoop eventLoop;
    volatile SelectionKey selectionKey;

    public Channel(){
        this(newSocket(DEFAULT_SELECTOR_PROVIDER));
    }

    public ChannelPipeline pipeline() {
        return pipeline;
    }

    public Channel(ServerSocketChannel channel) {
        config = new NioServerSocketChannelConfig(this);
        /**
         * 创建jdk的channel
         * 设置阻塞模式
         * 创建id
         *  unsafe :tcp读写底层操作
         * pipeline
         */
        ch = channel;
        id = 1;
        unsafe = "";
        pipeline = new ChannelPipeline();
        try {
            channel.configureBlocking(false);
        } catch (IOException e) {
            try {
                channel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    protected SelectableChannel javaChannel() {
        return ch;
    }

    public NioServerSocketChannelConfig config() {
        return config;
    }

    private static ServerSocketChannel newSocket(SelectorProvider provider) {
        try {
            return provider.openServerSocketChannel();
        } catch (IOException e) {
            throw new ChannelException(
                    "Failed to open a server socket.", e);
        }
    }

    /**
     * selector注册
     * 1.绑定线程eventLoop
     * 2.jdk底层注册（将jdk的channel注册到selector上去）
     * 3.事件回调
     * 4.channel注册成功事件传播
     */
    public final void register(EventLoop eventLoop){
        this.eventLoop = eventLoop;
        //判断eventLoop线程是否在Group中，否则添加到任务队列里

        //ops:关心的事件
        for(;;){
//            javaChannel().register(eventLoop,0,this);
        }

    }
}
