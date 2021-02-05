package com.hertz.nio;

import java.nio.IntBuffer;

/**
 * @author Hertz
 * @date 2020/8/16 17:19
 */
public class BasicBuffer {

    public static void main(String[] args) {

        IntBuffer intBuffer = IntBuffer.allocate(5);

        intBuffer.put(1);
        intBuffer.put(2);
        intBuffer.put(3);
        intBuffer.put(4);
        intBuffer.put(5);

        // 读写切换
        intBuffer.flip();

        bufferToString(intBuffer);

        intBuffer.flip();

        intBuffer.put(6);

      //  intBuffer.flip();

        bufferToString(intBuffer);
    }

    public static void bufferToString(IntBuffer buffer){
        while (buffer.hasRemaining()){
            System.out.println(buffer.get());
        }
        System.out.println("==================================================");
    }
}
