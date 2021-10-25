package com.dong.base.test.thread.morethread;


import com.dong.base.test.lock.Buffer;
import com.dong.base.test.lock.BufferAndLock2;

import java.util.concurrent.locks.Lock;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 参考连接  https://blog.csdn.net/shi2huang/article/details/80289155
 *
 */
public class TestThreadState2 {

    private static Object oLock = new Object();
    private static Lock lock= new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        System.out.println(".....start");
        TestThreadState2 testThreadState2 = new TestThreadState2();
//        testLockSuport();
//        testlockAwait();
//        testlock();
        testThreadState2.testsynWait2();
//        testsynWait();
//        testsyn();
//        testSleep();
    }

    /**
     * LockSupport  WAITING
     *
     * "t1" #13 prio=5 os_prio=0 tid=0x000000001bc60000 nid=0x9c8 waiting on condition [0x000000001c6be000]
     *    java.lang.Thread.State: WAITING (parking)
     *         at sun.misc.Unsafe.park(Native Method)
     *         at java.util.concurrent.locks.LockSupport.park(LockSupport.java:304)
     *         at com.dong.base.test.thread.morethread.TestThreadState2.lambda$testLockSuport$0(TestThreadState2.java:42)
     *         at com.dong.base.test.thread.morethread.TestThreadState2$$Lambda$1/1558600329.run(Unknown Source)
     *         at java.lang.Thread.run(Thread.java:775)
     *
     * @throws InterruptedException
     */
    public static void testLockSuport() throws InterruptedException {
        Thread t1 = new Thread(()->{
            LockSupport.park();
        },"t1");
        t1.start();
        System.out.println("t1:"+t1.getState());
        TimeUnit.SECONDS.sleep(15);
        System.out.println("t1:"+t1.getState());
        LockSupport.unpark(t1);
        System.out.println("t1:"+t1.getState());

    }

    /**
     * await-signal  WAITING
     *
     * "add线程1" #14 prio=5 os_prio=0 tid=0x000000001c1e1000 nid=0x2e0 waiting on condition [0x000000001ce2e000]
     *    java.lang.Thread.State: WAITING (parking)
     *         at sun.misc.Unsafe.park(Native Method)
     *         - parking to wait for  <0x00000006eddbd4b0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
     *         at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
     *         at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
     *         at com.dong.base.test.lock.BufferAndLock2.add(BufferAndLock2.java:30)
     *         at com.dong.base.test.lock.BufferAndLock2$AddThread.run(BufferAndLock2.java:83)
     *         at java.lang.Thread.run(Thread.java:775)
     */
    public static void testlockAwait(){
        BufferAndLock2.testMain();
    }

    /**
     * lock  WAITING
     *
     * "t3" #15 prio=5 os_prio=0 tid=0x000000001c4f5800 nid=0x66b0 waiting on condition [0x000000001d14f000]
     *    java.lang.Thread.State: WAITING (parking)
     *         at sun.misc.Unsafe.park(Native Method)
     *         - parking to wait for  <0x00000006c49f8cf8> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)
     *         at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
     *         at java.util.concurrent.locks.AbstractQueuedSynchronizer.parkAndCheckInterrupt(AbstractQueuedSynchronizer.java:836)
     *         at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireQueued(AbstractQueuedSynchronizer.java:870)
     *         at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquire(AbstractQueuedSynchronizer.java:1199)
     *         at java.util.concurrent.locks.ReentrantLock$NonfairSync.lock(ReentrantLock.java:209)
     *         at java.util.concurrent.locks.ReentrantLock.lock(ReentrantLock.java:285)
     *         at com.dong.base.test.thread.morethread.TestThreadState2.lambda$testlock$2(TestThreadState2.java:59)
     *         at com.dong.base.test.thread.morethread.TestThreadState2$$Lambda$3/1349277854.run(Unknown Source)
     *         at java.lang.Thread.run(Thread.java:775)
     * @throws InterruptedException
     */
    public static void testlock() throws InterruptedException {
        Thread t1 = new Thread(()->{
            while (true){
                lock.lock();
                try {
                    long maxSum = Integer.MAX_VALUE;
                    long sum =0;
                    while (sum<maxSum){
                        sum++;
                    }
                }finally {
                    lock.unlock();
                }
            }
        },"t1");
        t1.start();
        Thread t2 = new Thread(()->{
            while (true){
                lock.lock();
                try {
                    long maxSum = Integer.MAX_VALUE;
                    long sum =0;
                    while (sum<maxSum){
                        sum++;
                    }
                }finally {
                    lock.unlock();
                }
            }
        },"t2");
        t2.start();
        Thread t3 = new Thread(()->{
            while (true){
                lock.lock();
                try {
                    long maxSum = Integer.MAX_VALUE;
                    long sum =0;
                    while (sum<maxSum){
                        sum++;
                    }
                }finally {
                    lock.unlock();
                }
            }
        },"t3");
        t3.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("t1："+t1.getState());
        System.out.println("t2："+t2.getState());
        System.out.println("t3："+t3.getState());
        TimeUnit.SECONDS.sleep(10);
    }

    /**
     * wait(num)  TIMED_WAITING
     *
     * "t1" #13 prio=5 os_prio=0 tid=0x000000001b9a5000 nid=0x269c in Object.wait() [0x000000001c3bf000]
     *    java.lang.Thread.State: TIMED_WAITING (on object monitor)
     *         at java.lang.Object.wait(Native Method)
     *         - waiting on <0x00000006c49fa0e0> (a com.dong.base.test.thread.morethread.TestThreadState2)
     *         at com.dong.base.test.thread.morethread.TestThreadState2.lambda$testsynWait2$4(TestThreadState2.java:160)
     *         - locked <0x00000006c49fa0e0> (a com.dong.base.test.thread.morethread.TestThreadState2)
     *         at com.dong.base.test.thread.morethread.TestThreadState2$$Lambda$1/1558600329.run(Unknown Source)
     *         at java.lang.Thread.run(Thread.java:775)
     * @throws InterruptedException
     */
    public  void testsynWait2() throws InterruptedException {
        Thread t1 = new Thread(()->{
            synchronized (oLock){
                try {
                    System.out.println(Thread.currentThread().getName()+"...start");
                    oLock.wait(25000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"...end");
                System.out.println(Thread.currentThread().getName());
            }
        },"t1");
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("t1:"+t1.getState());
        TimeUnit.SECONDS.sleep(15);
    }

    /**
     * wait-notify blocked
     *
     * "producer-0" #13 prio=5 os_prio=0 tid=0x000000001c003800 nid=0x186c in Object.wait() [0x000000001cb0f000]
     *    java.lang.Thread.State: BLOCKED (on object monitor)
     *         at java.lang.Object.wait(Native Method)
     *         at java.lang.Object.wait(Object.java:502)
     *         at com.dong.base.test.lock.Buffer.put(Buffer.java:28)
     *         - locked <0x00000006eddb9320> (a com.dong.base.test.lock.Buffer)
     *         at com.dong.base.test.lock.Buffer$Producer.run(Buffer.java:65)
     *         at java.lang.Thread.run(Thread.java:775)
     */
    public static void testsynWait(){
       Buffer.testMain();
    }

    /**
     * synchronized  BLOCKED
     * "t3" #15 prio=5 os_prio=0 tid=0x000000001c09e000 nid=0xe30 waiting for monitor entry [0x000000001ccff000]
     *    java.lang.Thread.State: BLOCKED (on object monitor)
     *         at com.dong.base.test.thread.morethread.TestThreadState2.lambda$testsyn$2(TestThreadState2.java:58)
     *         - waiting to lock <0x00000006c49f26c0> (a java.lang.Object)
     *         at com.dong.base.test.thread.morethread.TestThreadState2$$Lambda$3/1349277854.run(Unknown Source)
     *         at java.lang.Thread.run(Thread.java:775)
     *
     * @throws InterruptedException
     */
    public static void testsyn() throws InterruptedException {
        Thread t1 = new Thread(()->{
            while (true){
                synchronized (oLock){
                    long maxSum = Integer.MAX_VALUE;
                    long sum =0;
                    while (sum<maxSum){
                        sum++;
                    }
                }
            }
        },"t1");
        t1.start();
        Thread t2 = new Thread(()->{
            while (true){
                synchronized (oLock){
                    long maxSum = Integer.MAX_VALUE;
                    long sum =0;
                    while (sum<maxSum){
                        sum++;
                    }
                }
            }
        },"t2");
        t2.start();
        Thread t3 = new Thread(()->{
            while (true){
                synchronized (oLock){
                    long maxSum = Integer.MAX_VALUE;
                    long sum =0;
                    while (sum<maxSum){
                        sum++;
                    }
                }
            }
        },"t3");
        t3.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("t1："+t1.getState());
        System.out.println("t1："+t2.getState());
        System.out.println("t1："+t3.getState());
        TimeUnit.SECONDS.sleep(10);
    }

    /**
     * sleep  TIMED_WAITING
     * "t1" #13 prio=5 os_prio=0 tid=0x000000001bed2800 nid=0x40c0 waiting on condition [0x000000001c8fe000]
     *    java.lang.Thread.State: TIMED_WAITING (sleeping)
     *         at java.lang.Thread.sleep(Native Method)
     *         at java.lang.Thread.sleep(Thread.java:358)
     *         at java.util.concurrent.TimeUnit.sleep(TimeUnit.java:386)
     *         at com.dong.base.test.thread.morethread.TestThreadState2.lambda$testSleep$0(TestThreadState2.java:21)
     *         at com.dong.base.test.thread.morethread.TestThreadState2$$Lambda$1/1109371569.run(Unknown Source)
     *         at java.lang.Thread.run(Thread.java:775)
     *
     * @throws InterruptedException
     */
    public static void testSleep() throws InterruptedException {
        Thread t1 = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1");
        t1.start();
        Thread t2 = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2");
        t2.start();
        Thread t3 = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2");
        t3.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("t1："+t1.getState());
        System.out.println("t1："+t2.getState());
        System.out.println("t1："+t3.getState());
        TimeUnit.SECONDS.sleep(10);
    }
}
