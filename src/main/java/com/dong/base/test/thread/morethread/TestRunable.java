package com.dong.base.test.thread.morethread;

import java.util.concurrent.TimeUnit;

/**
 * @author dongjunpeng
 * @Description  run方法抛出异常 上面捕获不到，不能通过方法抛出异常
 * @date 2021/9/13
 */
public class TestRunable {

    public static void main(String[] args) throws InterruptedException {

        MyRunable m = new MyRunable();
        Thread t = new Thread(m);
        try {
            t.start();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        t.join();
        System.out.println("..........end");

    }

    public static class MyRunable implements Runnable{
        @Override
        public void run() {

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            int aa =2;
//            if(aa==2){
//                throw new RuntimeException("11");
//            }
            int s = 1/0;
            System.out.println(".runable...end");

        }
    }

}
