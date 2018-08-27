package org.netty.SPI.dubbo;

@SPI
public interface ExtensionFactory {
    <T> T getExtension(Class<T> type, String name);
}
