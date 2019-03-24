package com.dong.base.lock;

import com.dong.base.lock.zk.ZookeeperImproveLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;

/**
 * Created by Administrator on 2018/3/6.
 */
public class TestLock  implements Runnable{

    private Logger logger = LoggerFactory.getLogger(TestLock.class);

    private static OrderCodeGenerator ong = new OrderCodeGenerator();

    private static final int NUM = 20;

    private static CountDownLatch cdl = new CountDownLatch(NUM);

//    private static Lock lock = new ReentrantLock();
//    private static Lock lock = new ZookeeperLock();
    private static Lock lock = new ZookeeperImproveLock();

    public void createOrder(){
        String orderCode = null;
        lock.lock();
       try {
           orderCode = ong.getOrderCode();
           System.out.println(Thread.currentThread().getName()
                   + " ============>" + orderCode);
           //.......业务代码
       }catch (Exception e){
           e.printStackTrace();
       }finally {
           lock.unlock();
       }


    }

    @Override
    public void run() {
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        createOrder();
    }

    public static void main(String [] args){
        for (int i=1;i<=NUM;i++){
            new Thread(new TestLock()).start();
            cdl.countDown();
        }
    }
}
