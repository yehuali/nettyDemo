package org.netty.SPI.dubbo;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ExtensionLoade_test {
    @Test
    public void test_getAdaptiveExtension_defaultAdaptiveKey() throws Exception {
        SimpleExt ext = ExtensionLoader.getExtensionLoader(SimpleExt.class).getAdaptiveExtension();
        Map<String, String> map = new HashMap<String, String>();
        URL url = new URL("p1", "1.2.3.4", 1010, "path1", map);
        String echo = ext.echo(url, "haha");
        System.out.println("echo:"+echo);
    }
}
