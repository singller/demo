package com.zjx.demo.IO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.IO.BIO
 * @date:2020/7/31
 **/
public class IOClient {

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 8000);
                while (true) {
                    try {
                        socket.getOutputStream().write((new Date() + ": hello world 1").getBytes());
                        socket.getOutputStream().flush();
                        Thread.sleep(2000);
                    } catch (Exception e) {
                    }
                }
            } catch (IOException e) {
            }
        }).start();

        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 8000);
                while (true) {
                    try {
                        socket.getOutputStream().write((new Date() + ": hello world 2").getBytes());
                        socket.getOutputStream().flush();
                        Thread.sleep(2000);
                    } catch (Exception e) {
                    }
                }
            } catch (IOException e) {
            }
        }).start();

        new Thread(() -> {
            try {
                //得到一个网络通道
                SocketChannel socketChannel = SocketChannel.open();
                socketChannel.configureBlocking(false);
                InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 8000);
                //链接服务器
                if(!socketChannel.connect(inetSocketAddress)){
                    while (!socketChannel.finishConnect()){
                        System.out.println("客户端链接不阻塞");
                    }
                }
                while (true){
                    String str = new Date() + ": hello world 3";
                    ByteBuffer wrap = ByteBuffer.wrap(str.getBytes());
                    int write = socketChannel.write(wrap);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(write+"============");
                }

            } catch (IOException e) {
            }
        }).start();

    }
}
