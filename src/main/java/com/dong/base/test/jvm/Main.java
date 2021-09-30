package com.dong.base.test.jvm;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/9/27
 */
public class Main {

    public static Object lock = new Object();

    public static void main(String[] args) {

       for (int i=0;i<30;i++){
           new Thread(()->{
               while (true){
                   System.out.println(Thread.currentThread().getName()+"....get lock");
                   synchronized (lock){
                       try {
                           TimeUnit.SECONDS.sleep(1);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                   }
                   System.out.println(Thread.currentThread().getName()+"....relase lock");
                   try {
                       Thread.sleep(new Random().nextInt(1000));
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           },"t-"+i).start();
       }

    }

}
