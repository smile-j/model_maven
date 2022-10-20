package com.dong.base.test.io.buffer;

import java.nio.ByteBuffer;

/**
 *  缓冲区分片
 *  只读缓存区
 *  直接缓存区
 *  内存映射文件io
 *
 *  Buffer 类他们都采用相似的方法进行管理数据，只是各自 管理的数据类型不同而已。都是通过如下方法获取一个 Buffer 对象：
 *
 * static XxxBuffer allocate(int capacity) : 创建一个容量为capacity 的 XxxBuffer 对象
 *
 *
 * 缓冲区的基本属性 Buffer 中的重要概念：
 *
 * 容量 (capacity) ：作为一个内存块，Buffer具有一定的固定大小， 也称为"容量"，缓冲区容量不能为负，并且创建后不能更改。
 *
 * 限制 (limit)：表示缓冲区中可以操作数据的大小 （limit 后数据不能进行读写）。缓冲区的限制不能 为负，并且不能大于其容量。 写入模式，限制等于 buffer的容量。读取模式下，limit等于写入的数据量。
 *
 * 位置 (position)：下一个要读取或写入的数据的索引。 缓冲区的位置不能为 负，并且不能大于其限制
 *
 * 标记 (mark)与重置 (reset)：标记是一个索引， 通过 Buffer 中的 mark() 方法 指定 Buffer 中一个 特定的 position，之后可以通过调用 reset() 方法恢 复到这 个 position.
 *
 * 标记、位置、限制、容量遵守以下不变式： 0 <= mark <= position <= limit <= capacity
 */
public class BufferTest {

    public static void main(String[] args) {
        ByteBuffer buf = ByteBuffer.allocateDirect(1024);
        String str = "发广告";
        System.out.println("len:"+str.length());
        buf.put(str.getBytes());
        buf.flip();
        byte[] dst = new byte[2];//创建一个界限为limit大小的字节数组
        buf.get(dst);//批量将limit大小的字节写入到dst字节数组中
        System.out.println(new String(dst, 0, buf.position()));//结果为itheima
        System.out.println(buf.position()+"<->"+buf.limit());
        while (buf.hasRemaining()){
//            System.out.println((char) buf.get());
            System.out.println(buf.position()+"<->"+buf.limit());
        }
        buf.flip();
        buf.clear();
        //buf.compact()清楚已经读过的；clear清除所有的
        System.out.println("before put");
        System.out.println(buf.position());// 0: 表示当前的位置为0
        System.out.println(buf.limit());// 1024: 表示界限为1024，前1024个位置是允许我们读写的
        System.out.println(buf.capacity());//1024：表示容量大小为1024
        buf.put("ghijkmn".getBytes());
        System.out.println("after put");
        System.out.println(buf.position());// 0: 表示当前的位置为0
        System.out.println(buf.limit());// 1024: 表示界限为1024，前1024个位置是允许我们读写的
        System.out.println(buf.capacity());//1024：表示容量大小为1024
        buf.flip();
        System.out.println("after read");
        System.out.println(buf.position());// 0: 表示当前的位置为0
        System.out.println(buf.limit());// 1024: 表示界限为1024，前1024个位置是允许我们读写的
        System.out.println(buf.capacity());//1024：表示容量大小为1024
        System.out.println("flip");
        while (buf.hasRemaining()){
            System.out.println((char) buf.get());
            System.out.println(buf.position()+"<->"+buf.limit());
        }
        System.out.println("0-----=========================-------------");
/*        System.out.println(buffer.position());// 0: 表示当前的位置为0
        System.out.println(buffer.limit());// 1024: 表示界限为1024，前1024个位置是允许我们读写的
        System.out.println(buffer.capacity());//1024：表示容量大小为1024
        System.out.println("----------------------------");
        System.out.println(buf.position());// 0: 表示当前的位置为0
        System.out.println(buf.limit());// 1024: 表示界限为1024，前1024个位置是允许我们读写的
        System.out.println(buf.capacity());//1024：表示容量大小为1024*/
//        test1();
//        test2();
//        test3();

    }

    public static void test1(){
        //1. 分配一个指定大小的缓冲区
//        ByteBuffer buf = ByteBuffer.allocate(1024);
        ByteBuffer buf = ByteBuffer.allocateDirect(1024);//直接缓存区域，加快io的速度
        System.out.println("-----------------allocate()----------------");
        System.out.println(buf.position());// 0: 表示当前的位置为0
        System.out.println(buf.limit());// 1024: 表示界限为1024，前1024个位置是允许我们读写的
        System.out.println(buf.capacity());//1024：表示容量大小为1024

        //2. 利用 put() 存入数据到缓冲区中
        System.out.println("-----------------put()----------------");
        String str = "itheima";
        buf.put(str.getBytes());
        System.out.println(buf.position());// 7表示下一个可以写入的位置是7,因为我们写入的字节是7个,从0开始已经写了7个，位置为8的position为7
        System.out.println(buf.limit());// 1024：表示界限为1024，前1024个位置是允许我们读写的
        System.out.println(buf.capacity());//1024：表示容量大小为1024

        //3. 切换读取数据模式
        System.out.println("-----------------flip()----------------");
        buf.flip();
        System.out.println(buf.position());// 0: 读取的起始位置为0
        System.out.println(buf.limit());// 7: 表示界限为7，前7个位置有数据可以读取
        System.out.println(buf.capacity());// 1024:表示容量大小为1024

        //4. 利用 get() 读取缓冲区中的数据
        System.out.println("-----------------get()----------------");
        byte[] dst = new byte[buf.limit()];//创建一个界限为limit大小的字节数组
        buf.get(dst);//批量将limit大小的字节写入到dst字节数组中
        System.out.println(new String(dst, 0, dst.length));//结果为itheima

        System.out.println(buf.position());//7: 读取的位置变为7,因为前面的7个字节数据已经全部读取出去,下一个可读取的位置为7，从0开始的
        System.out.println(buf.limit());//7: 可读取的界限大小为7
        System.out.println(buf.capacity());// 1024: 表示容量大小为1024

        //5. rewind() : 可重复读
        System.out.println("-----------------rewind()----------------");
        buf.rewind();// 将位置设为为 0,从头开始读取
        System.out.println(buf.position());// 0
        System.out.println(buf.limit());// 7
        System.out.println(buf.capacity());// 1024

        //6. clear() : 清空缓冲区. 但是缓冲区中的数据依然存在，但是处于“被遗忘”状态
        System.out.println("-----------------clear()----------------");
        buf.clear();
        System.out.println(buf.position());// 0
        System.out.println(buf.limit());// 1024
        System.out.println(buf.capacity());// 1024
        System.out.println(buf.hasRemaining());
        System.out.println((char)buf.get());//i
    }

    public static void test2(){
        String str = "itheima";

        ByteBuffer buf = ByteBuffer.allocate(1024);

        buf.put(str.getBytes());// 将str写入到buf缓冲区中

        buf.flip();//转换为读模式

        byte[] dst = new byte[buf.limit()];//定义一个字节数组
        buf.get(dst, 0, 2);//将前2个字节批量写入到dst字节数组中
        System.out.println(new String(dst, 0, 2));//打印结果为it
        System.out.println(buf.position());//当前下一个读取的位置为2

        //mark() : 标记
        buf.mark();

        buf.get(dst, 2, 2);//从第3个位置开始将2个字节批量写入到dst字节数组中
        System.out.println(new String(dst, 2, 2));//打印结果为he
        System.out.println(buf.position());// 当前下一个读取的位置为4

        //reset() : 恢复到 mark 的位置
        buf.reset();
        System.out.println(buf.position());// 2

        //判断缓冲区中是否还有剩余数据
        if(buf.hasRemaining()){
            //获取缓冲区中可以操作的数量
            System.out.println(buf.remaining());// 5: 返回 position 和 limit 之间的元素个数
        }

    }

    public static void test3(){
        //分配直接缓冲区
        ByteBuffer buf = ByteBuffer.allocateDirect(1024);
        System.out.println(buf.isDirect());

    }


}
