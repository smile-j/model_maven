package com.dong.base.test.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2018/2/6.
 */
public class TestPrintABC {
    public static void main(String[] args) throws InterruptedException {
        final PrintABC printABC = new PrintABC();
//        final CountDownLatch num = new CountDownLatch(3);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1;i<=10;i++) {
                    printABC.loopA(i);
                }
//                num.countDown();
            }
        },"线程A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1;i<=10;i++) {
                    printABC.loopB(i);
                }
//                num.countDown();
            }
        },"线程B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1;i<=10;i++) {
                    printABC.loopC(i);
                    System.out.println("-----------------------------------------------------------");
                }
//                num.countDown();

            }
        },"线程C").start();
    }
}

 class PrintABC {



    private int number = 1;//当前正在执行线程的标记
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void loopA(int totalLop){
        lock.lock();
      try {
          if(number!=1){
              condition1.await();
          }
          for(int i=1;i<=5;i++){
              System.out.println(Thread.currentThread().getName()+"\t"+i+"\t"+totalLop);
          }
          number=2;
          condition2.signal();
      } catch (InterruptedException e) {
          e.printStackTrace();
      }finally {
          lock.unlock();
      }
    }

    public void loopB(int totalLop){
        lock.lock();
        try {
            if(number!=2){
                condition2.await();
            }
            for(int i=1;i<=10;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i+"\t"+totalLop);
            }
            number=3;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void loopC(int totalLop){
        lock.lock();
        try {
            if(number!=3){
                condition3.await();
            }
            for(int i=1;i<=15;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i+"\t"+totalLop);
            }
            number=1;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
