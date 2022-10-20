package com.dong.base.test.io.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class MainChannel {

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("D:\\1.png");
        FileChannel channel01 = fileInputStream.getChannel();


        FileOutputStream fileOutputStream = new FileOutputStream("D:\\2.png");
        FileChannel channel02 = fileOutputStream.getChannel();

        channel02.transferFrom(channel01,0,channel01.size());

        channel01.close();
        channel02.close();
        fileInputStream.close();
        fileOutputStream.close();
    }

    public static void mainCopy(String[] args) throws IOException {

        FileInputStream fileInputStream = new FileInputStream("D:\\1.txt");
        FileChannel channel01 = fileInputStream.getChannel();


        FileOutputStream fileOutputStream = new FileOutputStream("D:\\2.txt");
        FileChannel channel02 = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        while (true){
            int read = channel01.read(byteBuffer);
            byteBuffer.clear();
            if(read==-1){
                break;
            }
            byteBuffer.flip();
            channel02.write(byteBuffer);
        }
        channel01.close();
        channel02.close();

    }

    public static void mainRead(String[] args) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(new File("D:\\1.txt"));
//        FileInputStream fileInputStream = new FileInputStream("D:\\1.txt");
        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer byteBuffer= ByteBuffer.allocate(512);
        channel.read(byteBuffer);
        byteBuffer.flip();

        System.out.println(new String(byteBuffer.array()));
        fileInputStream.close();

    }

    public static void mainWrite(String[] args) throws IOException {

        String msg ="hello java !";
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\1.txt");
        FileChannel fileChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer= ByteBuffer.allocate(512);

        byteBuffer.put(msg.getBytes());
        byteBuffer.flip();
        fileChannel.write(byteBuffer);
        fileOutputStream.close();


    }

}
