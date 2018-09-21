package org.imooc.netty;

import java.util.Map;

public class NioServerSocketChannelConfig {

    protected final Channel channel;

    public NioServerSocketChannelConfig(Channel channel){
        this.channel = channel;
    }

    public boolean setOptions(Map<String,Object> options) {
       for(Map.Entry<String,Object> a :options.entrySet()){
           //遍历设置
       }
       return true;
    }



}
