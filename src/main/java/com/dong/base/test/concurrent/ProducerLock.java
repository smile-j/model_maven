package com.dong.base.test.concurrent;

import java.util.concurrent.locks.Lock;

/**
 * Created by Administrator on 2018/1/23.
 */
public class ProducerLock implements Runnable {
    private Lock lock;
    public ProducerLock(Lock lock) {
        this. lock = lock;
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        int count = 10;
        while (count > 0) {
            try {
                lock.lock();

//                System. out.print( "A");
                System. out.println(Thread.currentThread().getName()+ "A "+count);
                count --;
            } finally {
                lock.unlock();
                try {
                    Thread. sleep(90L);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        System.out.println("ProducerLock is ok!");

    }
}
