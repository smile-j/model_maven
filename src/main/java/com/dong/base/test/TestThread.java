package com.dong.base.test;


import com.dong.base.test.thread.DIVRunnable;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by dong on 2016/8/31.
 */
public class TestThread  {


//    @Test
    public void testRunnable(){
//    public static void main(String[] args){
        Runnable runnable = new DIVRunnable();
        new Thread(runnable,"zf").start();
        new Thread(runnable,"lb").start();
        new Thread(runnable,"gy").start();
    }

    public static void main(String[] args) {
        Runnable runnable = new DIVRunnable();
//        new Thread(runnable,"aa").start();
//        new Thread(runnable,"bb").start();


//        new Thread(runnable,"zf").start();
//        new Thread(runnable,"lb").start();
//        new Thread(runnable,"gy").start();


        final boolean stop = false;
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("Someone interrupted me.");
                        return;
                    } else{
                        System.out.println("Thread is Going...");
                    }
                }
            }
        });
        System.out.println("mainmainmain");
        t.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.interrupt();
        System.out.println("main-----main线程运行结束结束");
    }


}
