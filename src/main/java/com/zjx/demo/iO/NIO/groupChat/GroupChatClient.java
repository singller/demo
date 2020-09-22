package com.zjx.demo.iO.NIO.groupChat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class GroupChatClient {
    //定义属性
    private Selector clientSelector;
    private SocketChannel socketChannel;
    private final static String ADDRESS = "127.0.0.1";
    private final static int PORT = 8001;
    private String userName;

    public GroupChatClient() throws IOException {
        clientSelector = Selector.open();
        socketChannel = SocketChannel.open(new InetSocketAddress(ADDRESS, PORT));
        socketChannel.configureBlocking(false);
        //注册
        socketChannel.register(clientSelector, SelectionKey.OP_READ);
        userName = socketChannel.getLocalAddress().toString();
    }

    //发送消息
    public void sendInfoToServer(String info){
        info = userName +"说：" +info;
        try {
            socketChannel.write(ByteBuffer.wrap(info.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //接收消息
    public void readinfo() throws IOException {

        if (clientSelector.select(1)>0){
            Set<SelectionKey> selectionKeys = clientSelector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                if(key.isReadable()){

                    socketChannel = (SocketChannel)key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int read = socketChannel.read(byteBuffer);
                    if(read>0){
                        System.out.println("收到消息："+new String(byteBuffer.array()));
                    }
                }
            }
            iterator.remove();
        }
    }


    public static void main(String[] args) throws IOException {
        GroupChatClient groupChatClient = new GroupChatClient();

        new Thread(()->{
            try {
                while (true){
                    groupChatClient.readinfo();
                    Thread.sleep(3000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String s = scanner.nextLine();
            groupChatClient.sendInfoToServer(s);
        }
    }
}
