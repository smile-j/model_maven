package com.dong.base.test.io.pipe;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * java nio管道是2个线程之间的单向数据连接。Pipe有一个source通道和一个sink通道。数据会被写到sink通道，从source通道读取。
 */
public class PipeDemo {

    public static void main(String[] args) throws Exception {
        //1.获取管道
        Pipe pipe = Pipe.open();
        //2.获取sink通道
        Pipe.SinkChannel sink = pipe.sink();
        //3.创建缓存区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("hello world ".getBytes());
        buffer.flip();
        //4.写入数据
        sink.write(buffer);
        //5.获取source通道
        Pipe.SourceChannel sourceChannel = pipe.source();
//        //6.读取数据
//        ByteBuffer buffer2 = ByteBuffer.allocate(1024);
        buffer.flip();
        int len = sourceChannel.read(buffer);
        System.out.println(len+" 输出:"+new String(buffer.array(),0,len));
        //7.关闭
        sourceChannel.close();
        sink.close();

    }

}
