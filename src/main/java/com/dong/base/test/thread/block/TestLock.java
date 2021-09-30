package com.dong.base.test.thread.block;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 *
 */
public class TestLock {

    public static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+" ... start ");
            lock.lock();
            try {
                List<Object> list = new ArrayList<>();
                for(int i=0;i<8000000;i++){
                    list.add(i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println(Thread.currentThread().getName()+" ... end ");
        },"t1");
        Thread t2 = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+" ... start ");
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println(Thread.currentThread().getName()+" ... end ");
        },"t2");
        t1.start();
        t2.start();
        TimeUnit.MILLISECONDS.sleep(10);
        System.out.println("t1 state "+t1.getState());
        System.out.println("t2 state "+t1.getState());
        t1.interrupt();
        System.out.println(Thread.currentThread().getName()+" ... end ");


    }

}
