package com.dong.base.test.concurrent;

import java.util.concurrent.locks.Lock;

/**
 * Created by Administrator on 2018/1/23.
 */
public class ConsumerLock implements Runnable {
    private Lock lock;
    public ConsumerLock(Lock lock) {
        this. lock = lock;
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        int count = 10;
        while( count > 0 ) {
            try {
                lock.lock();

//                System. out.print( "B");
                System. out.println(Thread.currentThread().getName()+ "B "+count);
                count --;
            } finally {
                lock.unlock(); //主动释放锁
                try {
                    Thread. sleep(91L);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    System.out.println("ConsumerLock is ok!");
    }
}
