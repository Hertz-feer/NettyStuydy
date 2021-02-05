package com.hertz.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author by Hertz
 * @Classname GroupChatServer
 * @Description 群聊客户端
 * @Date 2020/8/26 11:01
 */
public class GroupChatServer {

    private static final int PORT = 6399;
    private Selector selector = null;
    private ServerSocketChannel serverSocketChannel;

    public GroupChatServer() {
        try {
            selector = Selector.open();

            serverSocketChannel = ServerSocketChannel.open();

            serverSocketChannel.socket().bind(new InetSocketAddress(PORT));

            serverSocketChannel.configureBlocking(false);

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        System.out.println("聊天室开启");
        while (true) {
            try {
                int count = selector.select(2000);

                if (count > 0) {
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();

                    Iterator<SelectionKey> keyIterator = selectionKeys.iterator();

                    while (keyIterator.hasNext()) {
                        SelectionKey key = keyIterator.next();
                        if (key.isAcceptable()) {
                            SocketChannel accept = serverSocketChannel.accept();
                            accept.configureBlocking(false);
                            accept.register(selector, SelectionKey.OP_READ);
                            System.out.println("用户：" + accept.getRemoteAddress() + "加入群聊！！");
                        }else if (key.isReadable()){
                            read(key);
                        }
                        keyIterator.remove();
                    }
                }else {
                    System.out.println("当前聊天室人数为："+(selector.keys().size()-1));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void read(SelectionKey selectionKey){
        SocketChannel socketChannel = null;
        socketChannel= (SocketChannel) selectionKey.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            int readCount = socketChannel.read(buffer);
            if (readCount>0){
                String msg = new String(buffer.array());
                //发送到其他客户端
                System.out.println("用户"+socketChannel.getRemoteAddress()+"发送了消息"+msg);
                sendMsgToOther(msg,socketChannel);
            }
        } catch (IOException e) {
            try {
                System.out.println("用户："+socketChannel.getRemoteAddress()+"下线！");
                socketChannel.getRemoteAddress();
                selectionKey.channel();
                socketChannel.close();

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private void sendMsgToOther(String msg,SocketChannel self) throws IOException {
        Set<SelectionKey> allKey = selector.keys();
        Iterator<SelectionKey> keyIterator = allKey.iterator();

        while (keyIterator.hasNext()){
            SelectionKey key = keyIterator.next();
            SelectableChannel channel = key.channel();
            if (channel instanceof SocketChannel && !channel.equals(self)){
                ((SocketChannel) channel).write(ByteBuffer.wrap(msg.getBytes()));
            }
        }
    }

    public static void main(String[] args) {
        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.listen();
    }
}
