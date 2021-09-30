package com.dong.base.test.thread.block;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * park 后线程的状态 WAITING
 */
public class TestLockSupport {

    public static void main(String[] args) throws InterruptedException {
//        test01();
        testInterrupt();
    }

    /**
     * 中断线程2种方式：interrupt(); LockSupport.unpark(t1)
     * @throws InterruptedException
     */
    public static void testInterrupt() throws InterruptedException {
        String game= new String("游戏");
        Thread t1 = new Thread(()->{
            System.out.println("周末打游戏....");
            LockSupport.park();
            System.out.println("打篮球....");
        },"t1");
        t1.start();
        TimeUnit.SECONDS.sleep(2);
//        LockSupport.unpark(t1);
        t1.interrupt();
        TimeUnit.SECONDS.sleep(2);
        System.out.println(Thread.currentThread().getName()+"...end ");
    }

    /**
     * 调用 interrupt 会阻断 wait
     *
     * permit 不可用时，当前线程会被阻塞，直至发生以下三种情况
     *    1 其他线程调用 unpark 唤醒此线程
     * 　　2 其他线程通过 Thread#interrupt 中断此线程
     * 　　3 该调用不合逻辑地（即毫无理由地）返回，可能是操作系统异常导致的
     *
     */
    public static void test03() throws InterruptedException{
        Thread t1 = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"...start");
            System.out.println(Thread.currentThread().getName()+"...park");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName()+"...end");
        },"t1");
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("t1 state:"+t1.getState());
        t1.interrupt();
        System.out.println("t1 state:"+t1.getState());
        TimeUnit.SECONDS.sleep(2);
        System.out.println(Thread.currentThread().getName()+"...unpark");

    }

    public static void test02() throws InterruptedException {

        Thread t1 = new Thread(()->{
            System.out.println("...start");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("...park");
            LockSupport.park();
        },"t1");
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("start...unpark");
        LockSupport.unpark(t1);
        System.out.println("end...unpark");



    }

    //先park再unpark
    public static void test01() throws InterruptedException {
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
}
