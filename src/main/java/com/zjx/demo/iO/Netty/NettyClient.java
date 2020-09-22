package com.zjx.demo.iO.Netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Date;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.iO.Netty
 * @date:2020/7/31
 **/
public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                });

        Channel channel = bootstrap.connect("127.0.0.1", 8000).channel();

        int i =0;
        while (true) {
//            String msg =new Date() + ": hello world!"+(++i);
//            System.out.println(msg);
            channel.writeAndFlush(new Date() + ": hello world!"+(++i));
            Thread.sleep(2000);
        }
    }
}
