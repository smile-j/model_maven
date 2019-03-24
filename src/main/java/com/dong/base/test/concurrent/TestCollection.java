package com.dong.base.test.concurrent;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2018/2/2.
 *
 * 此包还提供了设计用于多线程上下文中的 Collection 实现：
 ConcurrentHashMap、 ConcurrentSkipListMap、 ConcurrentSkipListSet、
 CopyOnWriteArrayList 和 CopyOnWriteArraySet。当期望许多线程访问一个给
 定 collection 时， ConcurrentHashMap 通常优于同步的 HashMap，
 ConcurrentSkipListMap 通常优于同步的 TreeMap。当期望的读数和遍历远远
 大于列表的更新数时， CopyOnWriteArrayList 优于同步的 ArrayList。
 *
 */
public class TestCollection {

    @Test
    public void  testConcurrentHashMap(){
        final ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    concurrentHashMap.put(Thread.currentThread().getName()+i,i);
                }
            }
        },"线程A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    concurrentHashMap.put(Thread.currentThread().getName()+i,i);
                }
            }
        },"线程B").start();
        System.out.println("******"+concurrentHashMap.toString());
    }

    @Test
    public void testCopyOnWriteArrayList(){

        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.add("aaa");


    }


    public void testCountDownLatch(){
        //CountDownLatch:闭锁，在完成某些运算时，只有其他所有线程的运算全部完成，当前运算才继续执行
        CountDownLatch countDownLatch = new CountDownLatch(10);

        
    }

    @Test
    public void testABCABC(){
        final PrintABC printABC = new PrintABC();
        final CountDownLatch num = new CountDownLatch(3);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1;i<=10;i++) {
                    printABC.loopA(i);
                }
                num.countDown();
            }
        },"线程A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1;i<=10;i++) {
                    printABC.loopB(i);
                }
                num.countDown();
            }
        },"线程B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1;i<=10;i++) {
                    printABC.loopC(i);
                    System.out.println("-----------------------------------------------------------");
                }
                num.countDown();

            }
        },"线程C").start();
        try {
            num.await(2,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
