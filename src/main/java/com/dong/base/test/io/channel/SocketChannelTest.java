package com.dong.base.test.io.channel;

import lombok.SneakyThrows;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketChannelTest {

    @SneakyThrows
    public static void main(String[] args) {
//        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("www.baidu.com",80));
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("www.baidu.com",80));
        System.out.println(socketChannel.isOpen());
        System.out.println(socketChannel.isConnected());
        System.out.println(socketChannel.configureBlocking(false));//

        ByteBuffer byteBuffer = ByteBuffer.allocate(16);
        socketChannel.read(byteBuffer);
        socketChannel.close();
        System.out.println("read over");
    }

}
