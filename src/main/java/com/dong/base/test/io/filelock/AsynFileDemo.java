package com.dong.base.test.io.filelock;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

/**
 * 异步读文件
 */
public class AsynFileDemo {

    public static void main(String[] args) throws Exception{

        write2();


    }

    private static void write2() throws IOException{
        Path path = Paths.get("D:\\2.txt");
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path,StandardOpenOption.WRITE);

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("java js".getBytes());
        buffer.flip();

        fileChannel.write(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("write over");

            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {

            }
        });

    }

    private static void write() throws IOException{
        Path path = Paths.get("D:\\2.txt");
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path,StandardOpenOption.WRITE);
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        buffer.put("java js".getBytes());
        buffer.flip();
        Future<Integer> future = fileChannel.write(buffer, 0);

        while (!future.isDone());

        buffer.clear();
        fileChannel.close();
        System.out.println("write over");


    }

    private static void read2() throws IOException{
        Path path = Paths.get("D:\\2.txt");
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path,StandardOpenOption.READ);

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        fileChannel.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("result:"+result);
                System.out.println(new String(attachment.array(),0,attachment.limit()));
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {

            }
        });

    }

    public static void read() throws IOException {

        Path path = Paths.get("D:\\2.txt");
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path,StandardOpenOption.READ);

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        Future<Integer> future = fileChannel.read(buffer, 0);

        while (!future.isDone());

        buffer.flip();
        System.out.println(new String(buffer.array(),0,buffer.limit()));

    }

}
