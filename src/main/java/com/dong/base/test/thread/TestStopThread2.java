package com.dong.base.test.thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2020/12/1.
 */
public class TestStopThread2 {

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws InterruptedException {
        System.out.println("start ....   ..... ");
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true){
                        Thread.sleep(500);
                        System.out.println("1线程状态："+Thread.currentThread().getState());
                        System.out.println("1111************"+Thread.currentThread().getName()+Thread.currentThread().getId());
//                        Thread.currentThread().wait();
                    }
                }  catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        },"aaaa");

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true){
                        Thread.sleep(500);
                        System.out.println("2线程状态："+Thread.currentThread().getState());
                        System.out.println("2222************"+Thread.currentThread().getName()+Thread.currentThread().getId());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"bbbb");

        thread1.start();
        thread2.start();
        Thread.sleep(1000);
        System.out.println("000000000000000000000000000000");
        synchronized (thread1){
            thread1.wait();
        }
        System.out.println(thread1.getState()+"----33333---"+thread2.getState());
        Thread.sleep(2000);
        Thread.currentThread().notifyAll();

    }




}
