package org.netty.blockIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BlockIO {
    /**
     *同时只能处理一个连接，要管理多个并发客户端，需要为每个新的客户端吧Socket创建一个新的Thread
     * 1.在任何时候都可能有大量的线程处于休眠状态，只是等待输入或者输出数据就绪
     * 2.为每个线程的调用栈都分配内存，其默认值64kb到1 MB,具体取决于操作系统
     * 3.即使Java虚拟机在物理上可以支持非常大数量的线程，但是在达到极限之前，上下文切换开销很麻烦
     */
    public static void main(String[] args) {
        try {
            //创建一个新的ServerSocket，用以监听指定端口上的连接请求
            ServerSocket serverSocket = new ServerSocket(8080);
            //对accept()方法的调用将被阻塞，直到一个连接建立
            Socket clientSocket = serverSocket.accept();
            //这些流对象都派生于该套接字的流对象
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
            String request,response;
            while((request = in.readLine()) != null){//readLine()将会被阻塞，直到由换行符或者回车符结尾的字符串被读取
                if("Done".equals(request)){//如果客户端发送了"Done"，则退出处理循环
                    break;
                }
                //请求被传递给服务器的处理方法
                response = processRequest(request);
                //服务器的响应被发送了客户端
                out.println(response);
            }

        } catch (IOException e) {//ALt +Enter 捕获异常
            e.printStackTrace();
        }
    }
}
