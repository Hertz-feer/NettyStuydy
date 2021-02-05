package com.hertz.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author by Hertz
 * @Classname MapperBufferTest
 * @Description MapperBuffer将文件读到内存中后直接在内存中读写文件
 * @Date 2020/8/22 15:07
 */
public class MapperBufferTest {

    public static void main(String[] args) throws IOException {

        RandomAccessFile randomAccessFile = new RandomAccessFile("nio01.txt","rw");

        FileChannel channel = randomAccessFile.getChannel();

        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 10);

        map.put(0, (byte) 'T');

      //  channel.close();

        randomAccessFile.close();

    }
}