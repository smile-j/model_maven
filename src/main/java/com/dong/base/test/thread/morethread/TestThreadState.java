package com.dong.base.test.thread.morethread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 线程状态
 * 线程的状态：new、runnable、blocked((重点关注)、waiting((重点关注)、timed_waiting、terminated
 *
 */
public class TestThreadState {

    public static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        testWaiting02();
//        testBlocked();
    }

    public static void testTerminated(){
        Thread t1 = new Thread(()->{

        });
        Thread t2 = new Thread(()->{

        });
        t1.start();
        t2.start();
        System.out.println("t1 state:"+t1.getState());
        System.out.println("t2 state:"+t2.getState());

    }

    /**
     * Thread.sleep(long)
     *
     */
    public static void testTimedWaiting(){
        Thread t1 = new Thread(()->{

        });
        Thread t2 = new Thread(()->{

        });
        t1.start();
        t2.start();
        System.out.println("t1 state:"+t1.getState());
        System.out.println("t2 state:"+t2.getState());
    }

    /**
     *
     */
    public static void testWaiting03() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("start...");
            try {
                TimeUnit.SECONDS.sleep(1);//t1睡眠了一秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("park...");
            LockSupport.park();//t1线程一秒后暂停
            System.out.println("resume...");
        }, "t1");
        t1.start();

        TimeUnit.SECONDS.sleep(2);//主线程睡眠二秒
        System.out.println("t1 state:"+t1.getState());
        System.out.println("unpark...");
        LockSupport.unpark(t1);//二秒后由主线程恢复t1线程的运行

    }
    /**
     * Object.wait() 还是runnable
     */
    public static void testWaiting02(){
        Thread t1 = new Thread(()->{
            synchronized (lock){
                try {
                    lock.wait();
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(()->{
           synchronized (lock){
               try {
                   TimeUnit.SECONDS.sleep(1);
                   lock.notify();
                   System.out.println(Thread.currentThread().getName());
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });
        t1.start();
        t2.start();
        System.out.println("t1 state:"+t1.getState());
        System.out.println("t2 state:"+t2.getState());

    }

    /**
     * join() 还是在runnable
     */
    public static void testWaiting01() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        Thread t1 = new Thread(()->{
            List<Integer> list = new ArrayList<>();
            for (int i=0;i<80000000;i++){
                if(list.size()+2<Integer.MAX_VALUE){
                    list.add(i);
                }
            }
        });
        t1.start();
        System.out.println("1...t1 state:"+t1.getState());
        System.out.println("1...main state:"+Thread.currentThread().getState());
        t1.join();
        System.out.println("t1 state:"+t1.getState());
        System.out.println("main state:"+Thread.currentThread().getState());

    }

    public static void testBlocked(){
        Thread t1 = new Thread(()->{
            synchronized (lock){
                System.out.println(Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }

        });
        t1.start();
        Thread t2 = new Thread(()->{

            synchronized (lock){
                System.out.println(Thread.currentThread().getName());
            }
        });
        t2.start();
        System.out.println("t1 state:"+t1.getState());
        System.out.println("t2 state:"+t2.getState());
    }
}
