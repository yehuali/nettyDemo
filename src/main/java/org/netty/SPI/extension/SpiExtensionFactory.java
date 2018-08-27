package org.netty.SPI.extension;

import org.netty.SPI.dubbo.ExtensionFactory;
import org.netty.SPI.dubbo.ExtensionLoader;
import org.netty.SPI.dubbo.SPI;

public class SpiExtensionFactory implements ExtensionFactory {

    @Override
    public <T> T getExtension(Class<T> type, String name) {
        if (type.isInterface() && type.isAnnotationPresent(SPI.class)) {
            ExtensionLoader<T> loader = ExtensionLoader.getExtensionLoader(type);
            if (!loader.getSupportedExtensions().isEmpty()) {
                return loader.getAdaptiveExtension();
            }
        }
        return null;
    }

}
