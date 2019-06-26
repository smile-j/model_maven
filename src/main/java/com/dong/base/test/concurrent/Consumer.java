package com.dong.base.test.concurrent;

/**
 * Created by Administrator on 2018/1/23.
 */
public class Consumer  implements Runnable{
    @Override
    public synchronized void run() {
        // TODO Auto-generated method stub
        int count = 10;
        while(count > 0) {
            synchronized (TestLockandSynchronized. obj) {

//                System. out.print( "B");
                System. out.println(Thread.currentThread().getName()+ "B "+count);
                count --;
                TestLockandSynchronized. obj.notify(); // 主动释放对象锁

                try {
                    TestLockandSynchronized. obj.wait();

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
        TestLockandSynchronized.countDownLatch.countDown();
        System.out.println("Consumer is ok!");
    }
}
