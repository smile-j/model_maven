package com.dong.base.test.io.channel;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileChannelDemo1 {

    /**
     * fileChannel 读取数据到bufffer
     * @param args
     */
    public static void main(String[] args) throws Exception {
        testRead();
//        testWrite();

    }

    public static void testWrite()throws Exception{
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\FFOutput\\1.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        String newData ="hello 11 地方";
        buffer.clear();

        buffer.put(newData.getBytes());
        buffer.flip();
        while (buffer.hasRemaining()){
            fileChannel.write(buffer);
        }

        fileChannel.close();
        System.out.println(".........end");
    }

    public static void testRead() throws Exception {
        String fileName ="";
//        FileChannel channel = FileChannel.open(Paths.get(fileName), StandardOpenOption.READ);
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\FFOutput\\1.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int read = fileChannel.read(buffer);
        while (read!= -1){
            System.out.println("读取了："+read);
            buffer.flip();
            while (buffer.hasRemaining()){
                System.out.print((char) buffer.get());
            }
            buffer.clear();
            read = fileChannel.read(buffer);
            System.out.println();
        }
        System.out.println(".........end");
    }
}
