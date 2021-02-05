package com.hertz.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @author by Hertz
 * @Classname FourthFileChannel
 * @Description 文件拷贝
 * @Date 2020/8/18 16:44
 */
public class FourthFileChannel {

    public static void main(String[] args)  throws IOException {

        FileInputStream inputStream = new FileInputStream("test.png");

        FileChannel inputStreamChannel = inputStream.getChannel();

        FileOutputStream outputStream = new FileOutputStream("copy.png");

        FileChannel outputStreamChannel = outputStream.getChannel();

        outputStreamChannel.transferFrom(inputStreamChannel,0,inputStreamChannel.size());

        inputStream.close();

        outputStream.close();
    }
}
