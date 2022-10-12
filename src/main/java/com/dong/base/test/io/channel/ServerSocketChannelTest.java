package com.dong.base.test.io.channel;

import lombok.SneakyThrows;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

public class ServerSocketChannelTest {

    @SneakyThrows
    public static void main(String[] args) {

        //
        int port = 8888;
        ByteBuffer buffer = ByteBuffer.wrap("hello world!!!".getBytes());
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //绑定
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        //非阻塞
        serverSocketChannel.configureBlocking(false);
        //监听有新的连接接入
        while (true){
            SocketChannel sc = serverSocketChannel.accept();
            if(sc==null){
                System.out.println("等待连接......");
                TimeUnit.SECONDS.sleep(2);
            }else {
                System.out.println("incoming connection from :"+sc.socket().getRemoteSocketAddress());
                buffer.rewind();//指针0
                sc.read(buffer);
                sc.close();
            }

        }
    }

}
