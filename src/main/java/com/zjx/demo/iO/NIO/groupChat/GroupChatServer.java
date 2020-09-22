package com.zjx.demo.iO.NIO.groupChat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/*
    编写服务端
    1.链接服务端
    2.接收消息并且进行转发
 */
public class GroupChatServer {
    //定义公共的变量
    private Selector serverSelector;

    private Selector clientSelector;

    private ServerSocketChannel serverSocketChannel;

    private SocketChannel clientChannel;

    private final static int PORT = 8001;

    public GroupChatServer() throws IOException {

        serverSelector = Selector.open();
        clientSelector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //绑定端口
        serverSocketChannel.bind(new InetSocketAddress(PORT));
        //链接注册
        serverSocketChannel.register(serverSelector, SelectionKey.OP_ACCEPT);
    }

    /*
        获取链接的客户端
     */
    public void getServer() {

        //获取链接
        new Thread(() -> {
            while (true) {
                try {
                    if (!(serverSelector.select(1) > 0)) {
                        Set<SelectionKey> selectionKeys = serverSelector.selectedKeys();
                        Iterator<SelectionKey> iterator = selectionKeys.iterator();
                        while (iterator.hasNext()) {
                            SelectionKey key = iterator.next();
                            //判断是否为链接事件
                            if (key.isAcceptable()) {
                                //获取SocketChannel
                                clientChannel = ((ServerSocketChannel) key.channel()).accept();
                                //设置非阻塞
                                clientChannel.configureBlocking(false);
                                //发送注册
                                clientChannel.register(clientSelector, SelectionKey.OP_READ);
                                String remind = clientChannel.getRemoteAddress() + "上线了";
                                System.out.println(remind);
                                clientChannel.write(ByteBuffer.wrap(remind.getBytes()));
                            }
                            iterator.remove();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        //读取信息
        new Thread(() -> {
            while (true) {
                SocketChannel clientChannel1 = null;
                try {
                    if (!(clientSelector.select(1) > 0)) {
                        Set<SelectionKey> Keys = clientSelector.selectedKeys();
                        Iterator<SelectionKey> iterator = Keys.iterator();
                        while (iterator.hasNext()) {
                            SelectionKey key = iterator.next();
                            try {
                                //判断是否可读
                                if (key.isReadable()) {
                                    //获取对应key的channel通道
                                    clientChannel1 = (SocketChannel) key.channel();
                                    //定义buffer块
                                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                                    int read = clientChannel1.read(byteBuffer);
                                    if (read > 0) {
                                        String msg = new String(byteBuffer.array());
                                        System.out.println("接受消息：" + msg);
                                        //完成转发
                                        sendInfoToOthers(msg, clientChannel1);
                                    }
                                }

                            } catch (IOException e) {
                                try {
                                    String warn = clientChannel1.getRemoteAddress() + "下线了";
                                    System.out.println(warn);
//                                    clientChannel.write(ByteBuffer.wrap(warn.getBytes()));
                                    //取消注册
                                    key.channel();
                                    // 关闭通道
                                    clientChannel1.close();
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            } finally {
                                iterator.remove();
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }

    //转发消息
    public void sendInfoToOthers(String msg, SocketChannel self) throws IOException {
        System.out.println("消息转发中");
        //遍历所有通道 排除自己
        for (SelectionKey key : clientSelector.keys()) {
            //通过key取出对应的SocketChannel
            SocketChannel socketChannel = (SocketChannel) key.channel();
            //排除自己
            if (socketChannel != self) {
                //将msg存储到buffer
                ByteBuffer wrap = ByteBuffer.wrap(msg.getBytes());
                //将buffer写入SocketChannel
                socketChannel.write(wrap);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.getServer();
    }
}
