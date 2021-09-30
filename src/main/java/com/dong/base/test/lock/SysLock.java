package com.dong.base.test.lock;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SysLock {
    static  String str ="222";
    static  String str2 ="111";
    static LockObject lockObject = new LockObject("11",11);
    static LockObject lockObject2 = new LockObject("11",11);
    public static void main(String [] args){

        System.out.println("---------------->"+lockObject.equals(lockObject2));

        System.out.println("....main  ......start  ");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Runnable  r1 = ()->{
            System.out.println(Thread.currentThread().getName()+" 即将 lock "+format.format(new Date()));

            synchronized (lockObject){
                try {
                    System.out.println(Thread.currentThread().getName()+"  lock "+format.format(new Date()));
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+" relase lock "+format.format(new Date()));

        };

        Runnable r2 = ()->{
            System.out.println(Thread.currentThread().getName()+" 即将 lock "+format.format(new Date()));

            synchronized (lockObject2){
                try {
                    System.out.println(Thread.currentThread().getName()+"  lock "+format.format(new Date()));
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+" relase lock "+format.format(new Date()));
        };

        Thread t1 = new Thread(r1,"r1");
        Thread t2 = new Thread(r2,"r2");
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("..main  ......end  ");
    }


}
