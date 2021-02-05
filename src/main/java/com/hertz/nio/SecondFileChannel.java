package com.hertz.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Hertz
 * @Classname SecondFileChannel
 * @Description 读取文件信息
 * @date 2020/8/16 20:12
 */
public class SecondFileChannel {

    public static void main(String[] args) throws IOException {

        File file = new File("d:\\temporary\\nio01.txt");

        FileInputStream fileInputStream = new FileInputStream(file);

        FileChannel channel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());

        channel.read(byteBuffer);

        System.out.println(new String(byteBuffer.array()));




    }
}
