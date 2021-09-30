package com.dong.base.test.thread.block;

import java.util.concurrent.TimeUnit;

/**
 * wait-> WAITING -> notify-> BLOCKED
 * wait(timeout)-> TIMED_WAITING
 *
 */
public class TestWait {

    public static Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {
        test03();
    }

    /**
     * 调用 interrupt 会阻断 wait
     */
    public static void test03() throws InterruptedException{
        Thread t1 = new Thread(()->{
            synchronized (lock){
                System.out.println(Thread.currentThread().getName()+"... is start");
                try {
                    System.out.println("...wait");
                    lock.wait(5000);
                    System.out.println("...notify");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("... error");
                }
                System.out.println(Thread.currentThread().getName()+"... is end");
            }
        },"t1");
        Thread t2 = new Thread(()->{
            synchronized (lock){
                System.out.println(Thread.currentThread().getName()+"... is start");
                t1.interrupt();//如果当前线程在等待之前或在等待时被任何线程中断，则会抛出 InterruptedException 异常
//                lock.notify();
                System.out.println(Thread.currentThread().getName()+"... is end");
            }
        },"t2");
        t1.start();
        t2.start();

        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("t1 state:"+t1.getState());
        TimeUnit.SECONDS.sleep(2);
        System.out.println("t1 state:"+t1.getState());
    }

    public static void test02() throws InterruptedException {
        Thread t1 = new Thread(()->{
            synchronized (lock){
                try {
                    System.out.println("...wait");
                    lock.wait(1000);
                    System.out.println("...notify");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1");
        Thread t2 = new Thread(()->{

        },"t2");
        t1.start();
        t2.start();
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("t1 state:"+t1.getState());
        TimeUnit.SECONDS.sleep(2);
        System.out.println("t1 state:"+t1.getState());

    }


        public static void test01() throws InterruptedException {
        Thread t1 = new Thread(()->{
            synchronized (lock){
                System.out.println("... wait");
                try {
                    TimeUnit.SECONDS.sleep(1);
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1");

        Thread t2 = new Thread(()->{
            synchronized (lock){
                System.out.println("... notify");
                try {
                    System.out.println("t2... t1 state:"+t1.getState());
                    TimeUnit.SECONDS.sleep(2);
                    lock.notify();
                    System.out.println("t2... t1 state:"+t1.getState());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"t2");
        t1.start();
        t2.start();
        TimeUnit.MILLISECONDS.sleep(1500);
        System.out.println("main... t1 state:"+t1.getState());
        System.out.println("main... t2 state:"+t2.getState());

        TimeUnit.SECONDS.sleep(3);

    }

}
