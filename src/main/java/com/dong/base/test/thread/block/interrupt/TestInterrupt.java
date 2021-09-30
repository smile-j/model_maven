package com.dong.base.test.thread.block.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * 线程中断的使用
 * https://www.cnblogs.com/onlywujun/p/3565082.html
 *
 *  如果一个线程处于了阻塞状态（如线程调用了thread.sleep、thread.join、thread.wait、1.5中的condition.await、以及可中断的通道上的 I/O
 *  操作方法后可进入阻塞状态），则在线程在检查中断标示时如果发现中断标示为true，
 *  则会在这些阻塞方法（sleep、join、wait、1.5中的condition.await及可中断的通道上的 I/O 操作方法）调用处抛出InterruptedException异常，
 *  并且在抛出异常后立即将线程的中断标示位清除，即重新设置为false
 *
 * 抛出InterruptedException异常后，中断标示位会自动清除
 *
 */
public class TestInterrupt {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"-1-"+Thread.currentThread().isInterrupted());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                System.err.println(Thread.currentThread().getName()+"-error-"+Thread.currentThread().isInterrupted());
//                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
            System.out.println(Thread.currentThread().getName()+"-2-"+Thread.currentThread().isInterrupted());
         /*   while (true){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("---interrupted");
                    System.out.println(Thread.currentThread().getName()+"-3-"+Thread.currentThread().isInterrupted());
                    return;
                }
            }*/
        },"t1");
        t1.start();
        System.out.println(Thread.currentThread().getName()+"--"+t1.isInterrupted());
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();

        System.out.println(Thread.currentThread().getName()+"--"+t1.isInterrupted());
        t1.join();
        System.out.println("...end ");
    }

}
