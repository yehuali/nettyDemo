package org.netty.SPI.dubbo;

import  org.netty.SPI.dubbo.URL;

@SPI("impl1")
public interface SimpleExt {
    // @Adaptive example, do not specify a explicit key.
    //没有设置key,则使用"扩展点接口名的点分隔"
    @Adaptive
    String echo(URL url, String s);

    @Adaptive({"key1", "key2"})
    String yell(URL url, String s);

    // no @Adaptive
    String bang(URL url, int i);

}
