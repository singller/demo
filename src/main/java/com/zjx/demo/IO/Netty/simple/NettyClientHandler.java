package com.zjx.demo.IO.Netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.IO.Netty.simple
 * @date:2020/9/14
 **/
public class NettyClientHandler extends ChannelInboundHandlerAdapter {


    //读取客户端发送的消息
    /*
        当客户端有读事件就会触发该方法
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        //将msg转成一个ByteBuf
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("服务端恢复的消息是" + buf.toString(CharsetUtil.UTF_8));
        System.out.println("服务端发送地址是" + ctx.channel().remoteAddress());
    }

    //当通道就绪就会触发该方法


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client ctx" + ctx);
        ctx.writeAndFlush(Unpooled.copiedBuffer("你好 服务端(>^ω^<)喵~",CharsetUtil.UTF_8));
    }

//    //数据读取完毕
//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        ctx.writeAndFlush(Unpooled.copiedBuffer("你好 服务端(>^ω^<)喵~",CharsetUtil.UTF_8));
//    }

    //处理异常
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        ctx.close();
    }
}
