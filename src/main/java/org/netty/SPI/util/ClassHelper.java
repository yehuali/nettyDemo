package org.netty.SPI.util;

public class ClassHelper {
    public static ClassLoader getCallerClassLoader(Class<?> caller) {
        return caller.getClassLoader();
    }
}
