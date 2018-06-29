Channel接口
  基本的I/O操作（bind() connect() read() 和 write()）依赖于底层网络传输所提供的原语
  Netty的Channle接口降低了直接使用Socket类的复杂性
  
EventLoop接口
  用于处理连接的生命周期中所发生的事件
  关系：
    1.一个EventLoopGroup包含一个或者多个EventLoop
    2.一个EventLoop在它的生命周期内只和一个Thread绑定
    3.所有由EventLoop处理的I/O事件都将在它专有的Thread上被处理
    4.一个Channel在它的生命周期内只注册一个EventLoop
    5.一个EventLoop可能会被分配给一个或多个Channel
    --->消除了对于同步的需要
    
ChannelFuture接口
   Netty提供了ChannelFuture接口，其addListener()方法注册了一个ChannelFutureListener
     -->以便在某个操作完成时得到通知
   所有属于同一个Channel的操作都被保证其将以它们被调用的顺序被执行
   
ChannelHandler接口
  1.充当了所有入站和出站的应用程序逻辑的容器 
     -->ChannelHandler的方法是由网络事件触发的
  2.ChannelHandler派生的ChannelInboundHandler和ChannelOutboundHandler接口
  3.可以看作是处理往来ChannelPipeline事件（包括数据）的任何代码的通用容器
    
ChannelPipeline接口
 1.ChannelPipeline为ChannelHandler链提供了容器
    -->定义了用于在该链上传播入站和出站事件流的API
 2.ChannelPipeline是ChannelHandler的编排顺序
 3.当ChannelHandler被添加到ChannelPipeline时，将会被分配一个ChannelHandlerContext
   --->ChannelHandlerContext;代表了ChannelHandler和ChannelPipeline之间的绑定
                             被用于写出站数据
                             
========================================================================================
ChannelHandler
  Netty以适配器类的形式提供了大量默认的ChannelHandler实现
  ChannelPipeline中的每个ChannelHandler将负责把事件转发到链中的下一个ChanelHandler
  --->适配器类将自动执行这个操作
  自定义ChannelHandler经常会用到的适配器类
    1.ChannelHandlerAdapter
    2.ChannelInboundHandlerAdapter
    3.ChannelOutboundHandlerAdapter
    4.ChannelDuplexHandler
 ============================================================================================
 编码器和解码器
   所有由Netty提供的编码器/解码器适配器类都实现了ChannelOutboundHandler或者ChannelInboundHandler接口
   channelRead方法将调用由预置解码器所提供的decode()方法，并将已解码的字节转发给ChannelPipeline中的下一个ChannelInboundHandler
   
 抽象类SimpleChannelInboundHandler
   利用一个ChannelHandler来接收解码消息，并对该数据应用业务逻辑
   --->扩展基类SimpleChannelInboundHandler<T>,其中T表示要处理的消息的Java类型
   
==============================================================
服务器需要两组不同的Channel
  1.第一组将只包含一个ServerChannel
    -->代表服务器自身的已绑定到某个本地端口的正在监听的套接字
  2.第二组将包含所有已创建的用来处理传入客户端连接的Channel                      