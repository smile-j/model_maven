package com.dong.base.test.jvm;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * jstack:
 * -F:当线程挂起时﹐使用jstack pid 请求不被响应时﹐强制输出线程堆栈
 * -l:除堆栈外﹐显示矣于锁的附加信息,例如ownable synchronizers
 * -m : 可以同时输出java以及C/C++的堆栈信息
 *
 *
 * CPU占用过高:
 * 1.使用Process Explorer工具，找到CPU占用率高的进程的id ;
 * ⒉.右击该进程，查看属性，在thread选项卡中，找到cpu占用率高的线程id3.把线程id转换成16进制
 * 4.使用jstack -l <pid>查看进程的线程快照
 * 5.在线程快照中找到指定的线程，并分析代码
 *
 *
 *
 *
 *
 */
public class JstackTest {

    public static void main(String[] args) throws IOException {
//        System.out.println("jstack");
//        System.in.read();
        testDeadLock();

    }
    public static void testCpuHigh(){
        while (true){
            Date date = new Date();
            System.out.println("11-"+date);
        }
    }
    public static void testWait(){
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("...end");
    }
    public static void testDeadLock(){
        System.out.println("start");
        Object lock1 = new Object();
        Object lock2 = new Object();
        new Thread(()->{
            synchronized (lock1){
                System.out.println(Thread.currentThread().getName()+"--lock1");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2){
                    System.out.println(Thread.currentThread().getName()+"--lock2");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println(Thread.currentThread().getName()+"---end");
        },"t1").start();
        new Thread(()->{
            synchronized (lock2){
                System.out.println(Thread.currentThread().getName()+"--lock2");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1){
                    System.out.println(Thread.currentThread().getName()+"--lock1");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName()+"---end");
            }
        },"t2").start();
        System.out.println("end");
    }
}
