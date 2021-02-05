package com.hertz.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author by Hertz
 * @Classname ThirdFileChannel
 * @Description 文件转存
 * @Date 2020/8/18 16:10
 */
public class ThirdFileChannel {

    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream = new FileInputStream("d:\\temporary\\nio01.txt");

        FileChannel inputStreamChannel = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("nio01.txt");

        FileChannel outputStreamChannel = fileOutputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        int result = 0;

        while (result != -1) {

            buffer.clear();

            result = inputStreamChannel.read(buffer);

            buffer.flip();

            outputStreamChannel.write(buffer);

        }

        fileInputStream.close();

        fileOutputStream.close();

    }
}
