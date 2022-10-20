package com.dong.base.test.io.selector;

import lombok.SneakyThrows;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

public class SelectorClient {

    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",8888));
            socketChannel.configureBlocking(false);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            Scanner scanner = new Scanner(System.in);
            while (true){
                System.out.print("请输入:");
                String msg = scanner.nextLine();
                buffer.put(msg.getBytes());
                buffer.flip();
                socketChannel.write(buffer);
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
