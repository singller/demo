package com.zjx.demo.iO.Netty.simple;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.iO.Netty.simple
 * @date:2020/9/14
 **/
public class NettyClient {

    public static void main(String[] args) throws InterruptedException {

        //创建分组
        NioEventLoopGroup group = new NioEventLoopGroup();

        //创建启动器
        Bootstrap bootstrap = new Bootstrap();

        try{
            //绑定 及初始化设置
            bootstrap
                    .group(group) //进行绑定分组
                    .channel(NioSocketChannel.class)//服务器的通道链接
                    .handler(new ChannelInitializer<SocketChannel>() {//创建一个通道初始化对象
                        //给pipeline添加处理器
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new NettyClientHandler());
                        }
                    });
            System.out.println("客户端初始化好了");
            //绑定一个端口并且同步
            ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress("127.0.0.1", 8002)).sync();

            //对关闭通道进行监听
            ChannelFuture sync = channelFuture.channel().closeFuture().sync();
        }finally {
            //关闭
            group.shutdownGracefully();
        }

    }
}
