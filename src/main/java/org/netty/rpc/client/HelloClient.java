package org.netty.rpc.client;

import org.netty.rpc.api.HelloService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloClient {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("client.xml");
        RpcProxy rpcProxy = context.getBean(RpcProxy.class);
        HelloService helloService = rpcProxy.create(HelloService.class);
        String result = helloService.hello("World");
        System.out.println("result:"+result);
        System.exit(0);
    }
}
