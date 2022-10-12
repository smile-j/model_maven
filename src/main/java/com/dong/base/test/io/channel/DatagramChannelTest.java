package com.dong.base.test.io.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

public class DatagramChannelTest {

    //udp
    public static void main(String[] args) throws Exception {
        new Thread(()->{
            read();
        }).start();

      /*  new Thread(()->{
            sendDatagram();
        }).start();

        new Thread(()->{
            receiveDatagram();
        }).start();*/
      /*  DatagramChannel server = DatagramChannel.open();
        server.socket().bind(new InetSocketAddress(10086));

        ByteBuffer receiveBuffer = ByteBuffer.allocate(64);
        receiveBuffer.clear();
        SocketAddress receiveAddr = server.receive(receiveBuffer);
        System.out.println(receiveAddr);
        System.out.println(receiveBuffer.re);*/

    }

    //connection
    public static void read(){
        try {
            DatagramChannel server = DatagramChannel.open();
            ByteBuffer sendBuffer = ByteBuffer.wrap("client send 拉拉 ".getBytes());
            server.bind(new InetSocketAddress(10086));
            server.connect(new InetSocketAddress("127.0.0.1",10086));
            server.write(sendBuffer);

            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
            while (true){
                writeBuffer.clear();
                server.read(writeBuffer);
                writeBuffer.flip();
                System.out.println(Charset.forName("UTF-8").decode(writeBuffer));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //
    public static void sendDatagram(){
        try {
            DatagramChannel sendChannel = DatagramChannel.open();
            InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 8888);
            while (true){
                ByteBuffer buffer = ByteBuffer.wrap("hello ".getBytes("utf-8"));
                sendChannel.send(buffer,inetSocketAddress);
                System.out.println("发送完成");
                TimeUnit.SECONDS.sleep(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void receiveDatagram(){
        try {
            DatagramChannel sendChannel = DatagramChannel.open();
            InetSocketAddress inetSocketAddress = new InetSocketAddress(8888);
            sendChannel.bind(inetSocketAddress);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (true){
                buffer.clear();
                SocketAddress socketAddress = sendChannel.receive(buffer);
                buffer.flip();
                System.out.println(sendChannel.toString());
                System.out.println(Charset.forName("UTF-8").decode(buffer));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
