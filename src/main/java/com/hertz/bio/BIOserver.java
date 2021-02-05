package com.hertz.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Hertz
 * @date 2020/8/2 15:31
 */
public class BIOserver {

    private static Integer POST = 6211;

    public static void main(String[] args) throws IOException {

        ExecutorService pool = Executors.newCachedThreadPool();

        ServerSocket serverSocket = new ServerSocket(POST);
        System.out.println("客户端启动！！");
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("有一个连接接入");
            try {
                pool.execute(() -> {
                    doRead(socket);
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void doRead(Socket socket) {
        try {
            byte[] bytes = new byte[2048];

            InputStream is = socket.getInputStream();
            while (true) {
                int read = is.read(bytes);
                if (read != -1) {
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {

            }
        }


    }
}
