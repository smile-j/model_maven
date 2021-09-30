package com.dong.base.test.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Administrator on 2018/2/2.
 */
public class TestReentrantReadWriteLock {

    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    private int number =0;

    public static void main(String[] args)  {

      final TestReentrantReadWriteLock test = new TestReentrantReadWriteLock();
        for (int i=0;i<100;i++){
            new Thread("读线程"+i){
                public void run() {
                    test.get2(Thread.currentThread());
                };
            }.start();
        }

        for(int i=0;i<10;i++){
        new Thread("写线程"+i){
            public void run() {
                test.put2(Thread.currentThread(), (int) (Math.random()*1000));
            };
        }.start();
        }


    }


    public synchronized void get(Thread thread) {
        long start = System.currentTimeMillis();
        while(System.currentTimeMillis() - start <= 2) {
            System.out.println(thread.getName()+"正在进行读操作:"+number);
        }
        System.out.println(thread.getName()+"读操作完毕");
    }

    public  void get2(Thread thread) {
        reentrantReadWriteLock.readLock().lock();
//        reentrantReadWriteLock.writeLock().lock();
        try {
            long start = System.currentTimeMillis();
            while(System.currentTimeMillis() - start <= 1) {
                System.out.println(thread.getName()+"正在进行读操作:"+number);
            }
            System.out.println(thread.getName()+"读操作完毕");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            reentrantReadWriteLock.readLock().unlock();
//            reentrantReadWriteLock.writeLock().unlock();
        }

    }


    public  void put2(Thread thread,Integer number) {
//        reentrantReadWriteLock.readLock().lock();
        reentrantReadWriteLock.writeLock().lock();
        try {
            long start = System.currentTimeMillis();
            while(System.currentTimeMillis() - start <= 1) {
                this.number = number;
                System.out.println(thread.getName()+"正在进行写操作");
            }
            System.out.println(thread.getName()+"写操作完毕");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
//            reentrantReadWriteLock.readLock().unlock();
            reentrantReadWriteLock.writeLock().unlock();
        }

    }

}
