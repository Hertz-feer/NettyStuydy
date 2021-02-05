package com.hertz.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Hertz
 * @date 2020/8/26 21:30
 */
public class GroupChatClient02 {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 6399;
    private static Selector selector = null;
    private SocketChannel socketChannel;

    public GroupChatClient02( ) throws IOException {
        selector = Selector.open();

        socketChannel = SocketChannel.open();

        socketChannel.connect(new InetSocketAddress(HOST,PORT));

        socketChannel.configureBlocking(false);

        socketChannel.register(selector, SelectionKey.OP_READ);

        System.out.println("用户"+socketChannel.hashCode()+"登录成功！");
    }

    public void sendInfo(String msg){
        try {
            socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void read(){
        try {
            int count = selector.select(200);
            if (count>0){
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
                while (keyIterator.hasNext()){
                    SelectionKey key = keyIterator.next();
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    channel.read(buffer);
                    System.out.println("用户"+channel.getRemoteAddress()+"："+new String(buffer.array()));
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        GroupChatClient02 groupChatClient = new GroupChatClient02();
        new Thread(() -> {
            while (true){
                try {
                    Thread.sleep(1000);
                    groupChatClient.read();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Scanner scanner = new Scanner(System.in);
        while (true){
            String s = scanner.nextLine();
            groupChatClient.sendInfo(s);
        }
    }
}
