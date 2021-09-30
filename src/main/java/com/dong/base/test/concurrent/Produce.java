package com.dong.base.test.concurrent;

/**
 * Created by Administrator on 2018/1/23.
 */
public class Produce implements Runnable {
    @Override
    public void run() {
        // TODO Auto-generated method stub
        int count = 10;
        while(count > 0) {
            synchronized (TestLockandSynchronized. obj) {

                //System.out.print("count = " + count);
                System. out.println(Thread.currentThread().getName()+ "A "+count);
                count --;
                TestLockandSynchronized. obj.notify();

                try {
                    TestLockandSynchronized. obj.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
        TestLockandSynchronized.countDownLatch.countDown();
        System.out.println("Produce is ok!");
    }
}
