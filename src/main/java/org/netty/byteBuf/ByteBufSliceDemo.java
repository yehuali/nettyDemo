package org.netty.byteBuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;

public class ByteBufSliceDemo {

    @Test
    public void testSlice(){
       Charset utf8 =  Charset.forName("UTF-8");
       ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!",utf8);
       ByteBuf sliced = buf.slice(0,15);
       System.out.println(sliced.toString(utf8));
        /**
         * 数据是共享的，对其中一个所做的更改对另外一个也是可见的
         */
       buf.setByte(0,(byte)'J');
       assert buf.getByte(0) == sliced.getByte(0);
       System.out.println(sliced.toString(utf8));
    }

    @Test
    public void testCopyByteBuf(){
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!",utf8);
        ByteBuf copy = buf.copy();
        System.out.println(copy.toString());

        buf.setByte(0,'J');
        assert buf.getByte(0) == copy.getByte(0);
        System.out.println(copy.toString());
    }

    @Test
    public void testGet(){
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!",utf8);
        System.out.println((char)buf.getByte(0));

        int readerIndex = buf.readerIndex();
        int writerIndex = buf.writerIndex();

        buf.setByte(0,(byte)'B');
        System.out.println((char)buf.getByte(0));
        assert readerIndex == buf.readerIndex();
        assert writerIndex == buf.writerIndex();
    }

    @Test
    public void testReadAndWrite(){
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!",utf8);
        System.out.println(buf.readByte());

        int readIndex = buf.readerIndex();
        int writerIndex = buf.writerIndex();
        buf.writeByte((byte)'?');

        assert readIndex ==  buf.readerIndex();
        assert  writerIndex == buf.writerIndex();

    }
}
