package com.dong.base.test.concurrent;

import org.junit.Test;
import sun.misc.Unsafe;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2018/2/5.
 */
public class TestkeyWord {
// volatile
     volatile static int name ;
      static AtomicInteger atomicNum =new AtomicInteger(0) ;
//    private static final Unsafe unsafe = Unsafe.getUnsafe();
//    @Test
//    public void testVolatite(){
    public static void main(String [] args){
        System.out.println(name+"----------------------------"+atomicNum);
        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                Long correntDate = new Date().getTime();
                @Override
                public void run() {
                    while (new Date().getTime()-correntDate<10){

                        System.out.println(Thread.currentThread().getName()+"赋值name   :"+(name++));
                        System.out.println(Thread.currentThread().getName()+"赋值atomicNum   :"+(atomicNum.decrementAndGet()));
//                  try {
//                      Thread.sleep(10);
//                  } catch (InterruptedException e) {
//                      e.printStackTrace();
//                  }
                    }
                }
            }).start();
        }


    }


    private static  boolean stopThread;

    public void test2() throws InterruptedException {
//    public static void main(String [] args) throws InterruptedException {
        while (true) {
            final TestkeyWord test = new TestkeyWord();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.change();
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.print();
                }
            }).start();

        }


    }

    int a=1;
    int b=2;
    public void change(){
        a = 3;
        b = a;
    }

    public void print(){
        System.out.println("b="+b+";a="+a);
    }
}

