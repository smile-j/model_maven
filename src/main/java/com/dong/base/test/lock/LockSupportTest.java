package com.dong.base.test.lock;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {

    @Test
    public void testObjectLock() throws Exception {
        final Object obj = new Object();
        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                int sum = 0;
                for(int i=0;i<10;i++){
                    sum+=i;
                }
               synchronized (obj){
                   try {
                       obj.wait();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
                System.out.println(sum);
            }
        });
        A.start();
        //睡眠一秒钟，保证线程A已经计算完成，阻塞在wait方法
        Thread.sleep(1000);
       synchronized (obj){
           obj.notify();
       }
    }

    @Test
    public void testLockSupport() throws Exception {
        final Object obj = new Object();
        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                int sum = 0;
                for(int i=0;i<10;i++){
                    sum+=i;
                }
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"---->res:"+sum);
                LockSupport.park();
                System.out.println(Thread.currentThread().getName()+"--last---->res:"+sum);            }
        });
        A.start();
//        LockSupport.unpark(A);
        //睡眠一秒钟，保证线程A已经计算完成，阻塞在wait方法
        System.out.println(Thread.currentThread().getName()+"..sleep");
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName()+"..sleep over");
        LockSupport.unpark(A);
        A.join();

    }


    @Test
    public  void testLockSupportModel()throws Exception {
            ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(1000);
            ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5,5,1000, TimeUnit.SECONDS,queue);

            Future<String> future = poolExecutor.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    TimeUnit.SECONDS.sleep(5);
                    return "hello";
                }
            });
            String result = future.get();
            System.out.println(result);
        }


}
