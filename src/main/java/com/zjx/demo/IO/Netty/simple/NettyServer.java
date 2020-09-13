package com.zjx.demo.IO.Netty.simple;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.Socket;

public class NettyServer {

    public static void main(String[] args) throws InterruptedException {

        //创建分组
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        //创建启动器
        ServerBootstrap bootstrap = new ServerBootstrap();

        try{
            //绑定 及初始化设置
            bootstrap
                    .group(bossGroup,workGroup) //进行绑定分组
                    .channel(NioServerSocketChannel.class)//服务器的通道链接
                    .option(ChannelOption.SO_BACKLOG,128) //设置线程队列的连接个数
                    .childOption(ChannelOption.SO_KEEPALIVE,true)//设置保持活动链接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() {//创建一个通道初始化对象
                        //给pipeline添加处理器
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new NettyServerHandler());
                        }
                    });
            System.out.println("服务端初始化好了");
            //绑定一个端口并且同步
            ChannelFuture channelFuture = bootstrap.bind(8002).sync();

            //对关闭通道进行监听
            ChannelFuture sync = channelFuture.channel().closeFuture().sync();
        }finally {
            //关闭
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }
}
