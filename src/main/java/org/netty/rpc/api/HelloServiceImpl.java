package org.netty.rpc.api;

import org.netty.rpc.server.RpcService;

@RpcService(value = HelloService.class)
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "hello! "+name;
    }
}
