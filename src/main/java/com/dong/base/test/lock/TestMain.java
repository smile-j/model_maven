package com.dong.base.test.lock;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2018/2/2.
 */
public class TestMain {

    public static class Buffer{

        private LinkedList<String> list ;
        private int MaxSize;

        public Buffer(int maxSize,LinkedList list){
            this.list = list;
            this.MaxSize = maxSize;
        }

        public synchronized void add(String v) throws InterruptedException {
            if(this.list.size()==MaxSize){
                wait();
            }
            this.list.add(v);
            notifyAll();
        }
        public synchronized void offer() throws InterruptedException {
            if(this.list.size()==0){
                wait();
            }
            System.out.println(this.list.poll());
            notifyAll();
        }

    }

    public static class Consumer implements Runnable{

        private Buffer buffer ;
        public Consumer(Buffer buffer){
            this.buffer = buffer;
        }

        @Override
        public void run() {

        }
    }

    private static AtomicInteger count = new AtomicInteger(0);
    public static void main(String[] arg) throws InterruptedException {
        /*count.addAndGet(2);
        System.out.println(count.get());*/


        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(5);


        for (int i=0;i<3;i++){
            new Thread(()->{
                try {
                   while (true){
                       String s =1+(int)(Math.random()*100)+"";
                       TimeUnit.SECONDS.sleep(1);
                       blockingQueue.put(s);
                       System.out.println(Thread.currentThread().getName()+"--放入一个-"+s);
                   }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },i+"--生产者").start();
            new Thread(()->{
                try {
                   while (true){
                       System.out.println(Thread.currentThread().getName()+"--取出一个-"+blockingQueue.take());
                   }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },i+"--消费者").start();

        }

        TimeUnit.SECONDS.sleep(30);
    }

}
