package com.dong.base.test.concurrent;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2018/1/23.
 */
public class TestLockandSynchronized {
    public static final Object obj = new Object();

    @Test
    public void testLock(){
        System.out.println("**************************start");
        Lock lock = new ReentrantLock();

        ConsumerLock consumer = new ConsumerLock(lock);
        ProducerLock producer = new ProducerLock(lock);

        new Thread(consumer).start();
        new Thread(producer).start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("**************************end");
    }
    public static CountDownLatch countDownLatch = new CountDownLatch(2);
    @Test
    public void testSynchronized(){
        try {


        System.out.println("**************************start");
//        new Thread( new Produce()).start();
//        new Thread( new Consumer()).start();
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        ExecutorService executorservice = Executors.newFixedThreadPool(2);
//            new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2), Executors.defaultThreadFactory(), new RejectedExecutionHandler() {
//                @Override
//                public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
//                    System.out.println(r.toString() + " is rejected");
//                }
//            });
            System.out.println(executorservice+"  "+executorservice.getClass());
        executorservice.execute(new Produce());
        executorservice.execute(new Consumer());
            executorservice.shutdown();
//        executorservice.awaitTermination(1, TimeUnit.DAYS);
            while (!executorservice.awaitTermination(1, TimeUnit.SECONDS)) {
                System.out.println("线程池没有关闭");
            }
            executorservice.shutdown();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("**************************end");
        }
    }

    @Test
    public void testExecutor(){

        ReentrantLock lock = new ReentrantLock();

        System.out.println("***********************************************start");
        ExecutorService executorService = Executors.newScheduledThreadPool(10);
        for(int j=0;j<100;j++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    double sum=0;
                    for(int i=0;i<10000000;i++){
                        sum+=i;
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+": "+sum);
                }
            });
        }
        executorService.shutdown();
     /*   while(!executorService.isTerminated()){
            System.out.println("线程池没有关闭");
        }*/
        try {
            while (!executorService.awaitTermination(1,TimeUnit.SECONDS)){
                System.out.println("线程池没有关闭");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        try {
//            executorService.awaitTermination(1,TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("***********************************************end");
    }

    @Test
    public void test11(){
        int  a;
        ok:
        for(int i=0;i<10;i++) {
            for(int j=0;j<10;j++) {
                System.out.println("i=" + i + ",j= " + j);
                if(j == 5) break ok;
            }
            System.out.println("22222222");
        }

//        System.out.println("1111111111:"+a);
//       String bl="";
//        switch (bl){//byte short int char(及包装类) String(1.7)
//            case "11":{ break;}
//            case "22":{break;}
//            default:{
//
//            }
//        }
    }
}
