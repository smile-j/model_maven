package com.dong.base.test.lock;


import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/***
 * lockInterruptibly()方法比较特殊，当通过这个方法去获取锁时，如果其他线程正在等待获取锁，则这个线程能够响应中断，即中断线程的等待状态。
 * 也就使说，当两个线程同时通过lock.lockInterruptibly()想获取某个锁时，假若此时线程A获取到了锁，而线程B只有等待，
 * 那么对线程B调用threadB.interrupt()方法能够中断线程B的等待过程。
 *
 */
public class TestInterruptibly2 {

    @Test
    public void test3() throws Exception{
            final Lock lock=new ReentrantLock();
            lock.lock();
            Thread.sleep(1000);
            Thread t1=new Thread(new Runnable(){
                @Override
                public void run() {
//                    lock.lock();
	        	try {
					lock.lockInterruptibly();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                    System.out.println(Thread.currentThread().getName()+" interrupted.");
                }
            });
            t1.start();
            Thread.sleep(1000);
            t1.interrupt();
            Thread.sleep(1000000);
        }


        public static void main(String [] args){
            TestInterruptibly2 t = new TestInterruptibly2();
        }
}
