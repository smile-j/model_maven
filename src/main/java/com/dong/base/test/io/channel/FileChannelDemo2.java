package com.dong.base.test.io.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 通道的直接的传输
 */
public class FileChannelDemo2 {

    public static void main(String[] args) throws IOException {
        testTransferTo();
    }

    private static void testTransferTo() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\FFOutput\\1.txt", "rw");
        FileChannel fromChannel = randomAccessFile.getChannel();

        RandomAccessFile randomAccessFile2 = new RandomAccessFile("D:\\FFOutput\\3.txt", "rw");
        FileChannel toChannel = randomAccessFile2.getChannel();

        long postion =0;
        long size =fromChannel.size();
        fromChannel.transferTo(postion,size,toChannel);

        randomAccessFile.close();
        randomAccessFile2.close();
        System.out.println("============= ");
    }

    private static void testTransferFrom() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\FFOutput\\1.txt", "rw");
        FileChannel fromChannel = randomAccessFile.getChannel();

        RandomAccessFile randomAccessFile2 = new RandomAccessFile("D:\\FFOutput\\2.txt", "rw");
        FileChannel toChannel = randomAccessFile2.getChannel();

        long postion =0;
        long size =fromChannel.size();
        toChannel.transferFrom(fromChannel,postion,size);

        randomAccessFile.close();
        randomAccessFile2.close();
        System.out.println("============= ");
    }

}
