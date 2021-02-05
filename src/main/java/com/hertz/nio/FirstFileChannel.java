package com.hertz.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Hertz
 * @Classname FirstFileChannel
 * @Description 文件Channel
 * @date 2020/8/16 19:40
 */
public class FirstFileChannel {

    public static void main(String[] args) throws IOException {

        String str = "this is first file channel from idea 2020.2";

        FileOutputStream outputStream = new FileOutputStream("nio02.txt");

        FileChannel fileChannel = outputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1034);

        buffer.put(str.getBytes());

        buffer.flip();
        
        fileChannel.write(buffer);

        outputStream.close();

    }
}
