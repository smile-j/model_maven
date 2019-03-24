package com.dong.base.test;

import org.junit.Test;

import java.io.*;
import java.net.*;

/**
 * 1. IP 和 端口的具体意义:
 * 1). IP 定位网络中的一台主机
 * 2). 端口定位主机的一个网络程序.
 *
 * 2. InetAddress: 对象表示网络中的一个地址
 * InetAddress address = InetAddress.getByName("127.0.0.1");
 *
 * 3. TCP/IP 编程:
 * 1). 服务器/客户端: 客户端发送请求到服务器, 服务器接收请求, 给予响应到客户端.
 * 2). ServerSocket
 * 3). Socket
 * 具体参看 PPT 16 页的图.
 *
 */
public class SocketTest {

    @Test
    public void testURLConnection() throws IOException {
        URL url = new URL("http://127.0.0.1:8080/examples/abcd.txt");

        System.out.println(url.getPath());
        System.out.println(url.getQuery());

        URLConnection urlConnection = url.openConnection();
        System.out.println(urlConnection);

        InputStream in = urlConnection.getInputStream();
        OutputStream out = new FileOutputStream("test.txt");

        byte [] buffer = new byte[1024];
        int len = 0;

        while((len = in.read(buffer)) != -1){
            out.write(buffer, 0, len);
        }

        in.close();
        out.close();
    }

    @Test
    public void testClientSocket2() throws IOException {
        InetAddress address = InetAddress.getByName("127.0.0.1");
        Socket socket = new Socket(address, 8686);

        InputStream in = socket.getInputStream();
        OutputStream out = new FileOutputStream("d:\\abcd.jpg");

        byte [] buffer = new byte[1024];
        int len = 0;

        while((len = in.read(buffer)) != -1){
            out.write(buffer, 0, len);
        }

        in.close();
        out.close();

        socket.close();
    }

    @Test
    public void testServerSocket2() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8686);
        Socket socket = serverSocket.accept();

        InputStream in = new FileInputStream("abc.jpg");
        byte [] buffer = new byte[1024];
        int len = 0;

        OutputStream out = socket.getOutputStream();
        while((len = in.read(buffer)) != -1){
            out.write(buffer, 0, len);
        }

        out.close();
        in.close();

        socket.close();
        serverSocket.close();
    }

    @Test
    public void testSocket() throws IOException {
        InetAddress address = InetAddress.getByName("127.0.0.1");
        //创建 Socket 对象: 同时也向服务端发出请求
        Socket socket = new Socket(address, 8989);

        //通过 输入输出流 和服务端进行交互
        InputStream in = socket.getInputStream();
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(in));
        System.out.println("^_^: " + reader.readLine());

        in.close();
        reader.close();

        //关闭 Socket
        socket.close();
    }

    @Test
    public void testServerSocket() throws IOException {
        //创建 ServerSocket 对象
        ServerSocket serverSocket = new ServerSocket(8989);
        //接受客户端的请求, 并得到 Socket 对象

        Socket socket = serverSocket.accept();

        //通过 输入输出流 和客户端进行交互
        OutputStream out = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(out);
        writer.write("来自服务端的问候.");

        writer.close();
        out.close();

        //遍历 Socket 资源.
        socket.close();
        serverSocket.close();
    }

    /**
     * InetAddress: 表示互联网(或局域网)的一台主机的地址
     * @throws IOException
     */
    @Test
    public void testInetAddress() throws IOException {
        //InetAddress address = InetAddress.getByName("www.atguigu.com");
        //www.atguigu.com/202.108.35.210
        //System.out.println(address);

        InetAddress address2 = InetAddress.getLocalHost();
        System.out.println(address2);
    }

}
