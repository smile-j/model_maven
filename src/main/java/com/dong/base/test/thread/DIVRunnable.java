package com.dong.base.test.thread;

import sun.awt.windows.ThemeReader;

/**
 * Created by Administrator on 2018/1/24.
 */
public class DIVRunnable implements Runnable {

    private int appnum =5;
    private int fiveCont =1;
    boolean getApp(){
        if (appnum>0){
            appnum --;
            System.out.println(Thread.currentThread().getName()+"   剩余"+appnum);

            return true;
        }
        return false;
    }
    synchronized void buy() throws InterruptedException {
        Thread t= Thread.currentThread();

            if(t.getName().equals("zf")){
                if(fiveCont<4){
                    System.out.println("1张飞等着.....");
                    wait();
                  /*  while (fiveCont<3){
//                        Thread.sleep(100);死锁
//                        Thread.yield();
                        wait(11);
                        System.out.println("张飞等着.....");
                    }*/
                    System.out.println("张飞买一张.....");
                }else{
                    System.out.println("张飞买一张.....");
                }


        }else{
                if(t.getName().equals("lb")){
                    fiveCont++;
                    System.out.println("刘备买一张.....fiveNumber：" + fiveCont);
                }
                if(t.getName().equals("gy")){
                    fiveCont++;
                    System.out.println("关羽买一张.....fiveNumber："+fiveCont);
                }

        }
       if (fiveCont==3){
//            notifyAll();
            System.out.println("设置线程的中断标记 : "+t.getName());
            Thread.interrupted();
        }

    }

    @Override
    public void run() {
        //3. 24个字母俩个线程分别打印
       /* while (ss<='z'){
            printAz();
        }*/
     //  2. 张飞关羽等买票
       try {
            buy();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
      //1. 打印数子
   /*    while (getApp()){
           try {
               Thread.sleep(100);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }*/
    }

    private char ss ='a';

    public synchronized  void printAz(){
        if(ss<='z'){
            System.out.println(Thread.currentThread().getName()+" : "+ss);
            ss++;
            notify();
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
