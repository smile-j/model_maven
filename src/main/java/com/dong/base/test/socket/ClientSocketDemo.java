package com.dong.base.test.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSocketDemo {

    private static final String HOST = "127.0.0.1";

    private static final int PORT = 1234;

    public static void main(String[] args) throws UnknownHostException,
            IOException {
        Socket client = new Socket(HOST, PORT);
        OutputStream out = client.getOutputStream();
        InputStream is = client.getInputStream();
        byte [] bytes = new byte[512];
        try {
            while (true) {
                Thread.sleep(1000);
                System.out.println("发送心跳数据包");
                out.write("send heart beat data package !".getBytes());
                StringBuffer sb = new StringBuffer("");
                while (is.read(bytes)!=-1){
                    System.out.print(new String(bytes));
                    sb.append(bytes);
                }
                System.out.println("接受到的数据："+sb);
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.close();
            client.close();
        }
    }

}