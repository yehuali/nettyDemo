package org.netty.proxy.log;

import io.netty.channel.ChannelHandlerContext;
import org.netty.proxy.handler.ProxyChannelTrafficShapingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.*;
import java.util.Enumeration;

public class ProxyFlowLog4j  implements ProxyFlowLog{
    private static final Logger logger = LoggerFactory.getLogger(ProxyFlowLog4j.class);

    @Override
    public void log(ChannelHandlerContext ctx) {
        ProxyChannelTrafficShapingHandler trafficShapingHandler = ProxyChannelTrafficShapingHandler.get(ctx);
        InetSocketAddress localAddress = (InetSocketAddress) ctx.channel().localAddress();
        InetSocketAddress remoteAddress = (InetSocketAddress) ctx.channel().remoteAddress();

        long readByte = trafficShapingHandler.trafficCounter().cumulativeReadBytes();
        long writeByte = trafficShapingHandler.trafficCounter().cumulativeWrittenBytes();

        logger.info("{},{},{},{}:{},{}:{},{},{},{}",
                trafficShapingHandler.getUsername(),
                trafficShapingHandler.getBeginTime(),
                trafficShapingHandler.getEndTime(),
                getLocalAddress(),
                localAddress.getPort(),
                remoteAddress.getAddress().getHostAddress(),
                remoteAddress.getPort(),
                readByte,
                writeByte,
                (readByte + writeByte)
                );

    }

    /**
     * 获取本机的IP
     */
    private static String getLocalAddress(){

        try {
            for( Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces(); interfaces.hasMoreElements();){
                NetworkInterface networkInterface = interfaces.nextElement();
                if(networkInterface.isLoopback() || networkInterface.isVirtual() || !networkInterface.isUp()){
                    continue;
                }
                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                if(addresses.hasMoreElements()){
                    InetAddress address = addresses.nextElement();
                    if(address instanceof Inet4Address){
                        return address.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

        return "127.0.0.1";
    }
}