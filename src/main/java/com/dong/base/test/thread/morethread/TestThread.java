package com.dong.base.test.thread.morethread;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dongjunpeng
 * @Description  与Runable一样
 * @date 2021/9/13
 */
public class TestThread {


    public static Object lock = new Object();
    public static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        testState();

       /* Thread1 thread = new Thread1();
        try {
            thread.start();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        thread.join();
        System.out.println("...........end");*/

    }
    public static void testState() throws InterruptedException {

        Thread thread1 = new Thread(()->{
//            synchronized (lock)
//            reentrantLock.lock();
            synchronized (lock) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(1111);

            }/*finally {
                reentrantLock.unlock();
           }*/
        });
        thread1.start();
        Thread thread = new Thread(()->{
            BigDecimal sum = new BigDecimal("0");
//            reentrantLock.lock();
            synchronized (lock){
                for (int i=0;i<10;i++){
                    sum = sum.add(BigDecimal.valueOf(i));
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }/*finally {
                reentrantLock.unlock();
            }*/
            System.out.println("last:"+sum);
        });
        System.out.println("1:"+thread.getState());
        thread.start();
        System.out.println("2:"+thread.getState());
        System.out.println("3:"+thread.getState());
        TimeUnit.SECONDS.sleep(1);
        System.out.println("4:"+thread.getState());
        TimeUnit.SECONDS.sleep(1);
        System.out.println("5:"+thread.getState());
        TimeUnit.SECONDS.sleep(2);
        System.out.println("6:"+thread.getState());
    }

    public static class Thread1 extends Thread{

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int aa =2;
            if(aa==2){
                throw new RuntimeException("11");
            }
            int s = 1/0;
            System.out.println("end");
        }
    }

}
