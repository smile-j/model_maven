package com.dong.base.test.jvm;

import com.dong.base.model.UserEntity;

import java.io.IOException;

/**
 *
 * jmap命令:
 * jmap pid
 * 描述：查看进程的内存映像信息
 * 使用不带选项参数的jmap打印共享对象映射，将会打印目标虚拟机中加载的每个共享对象的超始地址，映射大小以及共享对象文件的路径全称
 *
 * 命令:jmap -heap pid
 * 擂球·显示Java堆详细信息
 * 打印一个堆的摘要信息﹐包括使用的GC算法，堆配置信息和各内存区域内存使用信息
 *
 * 命今：jmap -histo:live pid
 * 命令:jmap-histo pid
 * 描述∶显示堆中对象的绒计信息
 * 其中包括每个Java类，对象数量·内存大小(单位∶字节)·完全限定的类名·打印的虚拟机内部的类名称将会带有一个‘*’前缀，如果指定了live子兹项·则只计算活动的对象
 *
 * 命令:jmap -clstats pid
 * 描述：打印类加载器信息
 * -clstats是-permstat的替代方案·在JDK8之前·-permstat用来打印类加载器的数据
 * 打印java堆内存的永久保存区域的类加载器的智能统计信息﹔对于每个类加载器而言·它的名称·活联度·地址·父类加载器·它所加载的类的数量和大小都会被打印·此外
 *
 * 命令:jmap -finalizerirfo pid
 * 描述：打印等待终结的对象信息
 * Number of objects pending for finaLiztion:0说明当前F-Queue队列中并没有等待Finalizer线程执行finaLizer方法的对象﹔
 *
 * 命令: jmap -dump :Live ,format=b,fiLe=jmap.bin pid
 * 描威·生成堆转储快照dump文件
 * 以二进制格式存储java堆到指定filename的文件中-live子选项是可选的·如果指定了Live子进项·堆中只有活动的对象会被存储·想要州的heap dump·
 * 你可以使用jtat
 *
 */
public class JmapTest {


    public static void main(String[] args) throws IOException {
        System.out.println("jmap");
        JmapTest jmapTest = new JmapTest();
        UserEntity user = new UserEntity();
        System.in.read  ();
    }

}
