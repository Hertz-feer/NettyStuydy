package com.hertz.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author Hertz
 * @Classname NioSelecClient
 * @Description select客户端
 * @date 2020/8/23 17:32
 */
public class NioSelectClient01 {

    public static void main(String[] args) throws IOException {

        SocketChannel socketChannel = SocketChannel.open();

        socketChannel.configureBlocking(false);

        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 6399);

        if (!socketChannel.connect(address)) {

            while (!socketChannel.finishConnect()) {
                System.out.println("没有客户端可以连接！！");
            }
        }

        String sql = "SELECT * FROM DBA";

        socketChannel.write(ByteBuffer.wrap(sql.getBytes()));

        System.out.println("消息已经发送，即将进入等待！");

        while (true) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int read = socketChannel.read(buffer);
            if (read > 0){
                System.out.println("收到消息"+new String(buffer.array()));
            }
        }
    }
}
