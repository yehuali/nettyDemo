package org.netty.SPI.extension;

import org.netty.SPI.dubbo.SimpleExt;
import org.netty.SPI.dubbo.URL;

public class SimpleExtImpl1 implements SimpleExt {
    public String echo(URL url, String s) {
        return "Ext1Impl1-echo";
    }

    public String yell(URL url, String s) {
        return "Ext1Impl1-yell";
    }

    public String bang(URL url, int i) {
        return "bang1";
    }
}
