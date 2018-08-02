package org.netty.proxy.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 后端编码器
 */
public class BackendEncode extends MessageToByteEncoder<byte[]> {

    private static final Logger logger = LoggerFactory.getLogger(FrontendDecode.class);

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, byte[] msg, ByteBuf out) throws Exception {
        logger.info(String.format("发送出的报文:[%s]",ByteBufUtil.hexDump(msg)));
        out.writeBytes(msg);
    }
}
