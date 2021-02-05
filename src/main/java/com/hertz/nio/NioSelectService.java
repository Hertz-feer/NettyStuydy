package com.hertz.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author by Hertz
 * @Classname NioSelectService
 * @Description selector
 * @Date 2020/8/23 16:58
 */
public class NioSelectService {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(6501));

        serverSocketChannel.configureBlocking(false);

        Selector selector = Selector.open();

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){

            if (selector.select(1000) == 0){
                continue;
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();

            while (keyIterator.hasNext()){

                SelectionKey key = keyIterator.next();

                if (key.isAcceptable()){
                    SocketChannel accept = serverSocketChannel.accept();
                    System.out.println("得到了一个连接："+accept.hashCode());

                    accept.configureBlocking(false);
                    System.out.println("得到了一个连接,并设置为非阻塞："+accept.hashCode());

                    accept.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    System.out.println("有一个连接接入，已经把他注册到selector");
                }else if (key.isReadable()){

                    SocketChannel socketChannel = (SocketChannel) key.channel();

                    ByteBuffer buffer = (ByteBuffer) key.attachment();

                    socketChannel.read(buffer);

                    System.out.println("接受到了客户端的数据："+new String(buffer.array()));

                }

                keyIterator.remove();
            }
        }
    }
}
