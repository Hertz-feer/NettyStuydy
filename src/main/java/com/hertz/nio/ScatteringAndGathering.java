package com.hertz.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @author by Hertz
 * @Classname ScatteringAndGathering
 * @Description 分散和聚合
 * @Date 2020/8/22 15:23
 */
public class ScatteringAndGathering {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(8089));

        ByteBuffer[] byteBuffers = new ByteBuffer[2];

        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(6);

        SocketChannel accept = serverSocketChannel.accept();

        int maxByte = 11;

        while (true){

            int readByte = 0;

            while (readByte < maxByte){

                long read = accept.read(byteBuffers);

                readByte += read;

                Arrays.stream(byteBuffers).map(byteBuffer -> "索引："+byteBuffer.position()+"最大："+byteBuffer.limit()).forEach(System.out::println);

            }

            Arrays.asList(byteBuffers).forEach(Buffer::flip);

            long writeByte = 0;

            while (writeByte < maxByte){
                long i = accept.write(byteBuffers);
                writeByte += i;
            }

            Arrays.stream(byteBuffers).forEach(Buffer::clear);

            System.out.println("读取"+readByte+"======发送"+writeByte);


        }


    }
}
