package com.dong.base.test.lock;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/2.
 */
//模拟生产和消费的对象
public class Buffer {

    private int maxSize;
    private List<Date> storage;

    Buffer(int size) {
        maxSize = size;
        storage = new LinkedList<Date>();
    }

    //生产方法
    public synchronized void put() {
        try {
            while (storage.size() == maxSize) {
                System.out.println(Thread.currentThread().getName() + ": wait ");
                wait();
            }
            storage.add(new Date());
            System.out.print(Thread.currentThread().getName()+": put:"+storage.size()+ "\n");
//            Thread.sleep(1000);
            notifyAll();//唤起线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //消费方法
    public synchronized void take(){
        try {
            while (storage.size() ==0 ){//如果队列满了
                System.out.print(Thread.currentThread().getName()+": wait \n");;
                wait();//阻塞线程
            }
            Date d=((LinkedList<Date>)storage).poll();
            System.out.println(Thread.currentThread().getName()+": take:"+storage.size()+ "       "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d));
//            Thread.sleep(1000);
            notifyAll();//唤起线程
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //生产者
    static class Producer implements Runnable{
        private Buffer buffer;
        Producer(Buffer b){
            buffer=b;
        }
        @Override
        public void run() {
            while(true){
                buffer.put();
            }
        }
    }
    //消费者
    static class Consumer implements Runnable{
        private Buffer buffer;
        Consumer(Buffer b){
            buffer=b;
        }
        @Override
        public void run() {
            while(true){
                buffer.take();
            }
        }
    }


        public static void main(String[] arg){
            testMain();
        }

        public static void testMain(){
            Buffer buffer=new Buffer(10);
            Producer producer=new Producer(buffer);
            Consumer consumer=new Consumer(buffer);
            //创建线程执行生产和消费
            for(int i=0;i<3;i++){
                new Thread(producer,"producer-"+i).start();
            }
            for(int i=0;i<3;i++){
                new Thread(consumer,"consumer-"+i).start();
            }
        }

}
