package com.dong.base.test.lock;

import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2018/2/2.
 */
public class TestReentrantLock {

    private static ArrayList arrayList = new ArrayList<Integer>();
    Lock lock = new ReentrantLock();    //注意这个地方


        @Test
    public void test1(){
//    public static void main(String[] args) {
        final TestReentrantLock testReen = new TestReentrantLock();

        new Thread(new Runnable() {
            @Override
            public void run() {
                testReen.insertTryLock(Thread.currentThread());

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                testReen.insertTryLock(Thread.currentThread());
            }
        }).start();

        System.out.println("***********************" + arrayList.toString());
    }

    @Test
    public void test2() {
//        public static void main(String[] args) {
        TestReentrantLock test = new TestReentrantLock();
        MyThread thread1 = new MyThread(test);
        MyThread thread2 = new MyThread(test);
        thread1.start();
        thread2.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.interrupt();
    }

    public void insertAndLock(Thread thread) {

        lock.lock();
        try {
            System.out.println(thread.getName() + "得到了锁");
            for (int i = 0; i < 5; i++) {
                arrayList.add(i);
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            System.out.println(thread.getName() + "释放了锁");
            System.out.println("***********************" + arrayList.toString());
            lock.unlock();

        }
    }

    public void insertTryLock(Thread thread) {
        if (lock.tryLock()) {
            try {
                System.out.println(thread.getName() + "得到了锁");
                for (int i = 0; i < 5; i++) {
                    arrayList.add(i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("***********************" + arrayList.toString());
                System.out.println(thread.getName() + "释放了锁");
                lock.unlock();
            }
        } else {
            System.out.println(thread.getName() + "获取锁失败");
        }
    }


    public void insertInterruptibly(Thread thread) throws InterruptedException {
        lock.lockInterruptibly();   //注意，如果需要正确中断等待锁的线程，必须将获取锁放在外面，然后将InterruptedException抛出
        try {
            System.out.println(thread.getName() + "得到了锁");
            long startTime = System.currentTimeMillis();
                    for (; ; ) {
                if (System.currentTimeMillis() - startTime >= Integer.MAX_VALUE)
                    break;
                //插入数据
            }
        } finally {
            System.out.println(Thread.currentThread().getName() + "执行finally");
            lock.unlock();
            System.out.println(thread.getName() + "释放了锁");
        }
    }

    class MyThread extends Thread {
        private TestReentrantLock test = null;

        public MyThread(TestReentrantLock test) {
            this.test = test;
        }

        @Override
        public void run() {

            try {
                test.insertInterruptibly(Thread.currentThread());
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "被中断");
            }
        }

    }
}
