package com.dong.base.test.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MoreServer {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(9999);

            while (true){
                Socket socket = serverSocket.accept();
                new Thread(()->{
                    try {
                        InputStream inputStream = socket.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        String readLine ;
                        while ((readLine=bufferedReader.readLine())!=null){
                            System.out.println(readLine);
                        }

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
