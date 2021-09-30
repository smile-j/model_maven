package com.dong.base.test.thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2018/1/20.
 * 通过计数器的方式来做限流操作（1分钟之内接口请求不能超过100次）
 *
 */
public class CountDemo {

    private static int reqCount= 0;

    private static final int limit = 100;

    private static final long interval=1000;//间隔时间

    private static long timestamp = System.currentTimeMillis();

    private static boolean canAccess(){
        long now = System.currentTimeMillis();
        if(now<timestamp+interval){
            reqCount++;
            return reqCount<=limit;
        }

        timestamp = now;
        reqCount=1;
        return true;
    }

    public static void main(String [] args){

        ExecutorService service = Executors.newFixedThreadPool(2000);

        Work work = new Work();
        for (int i=0;i<20000;i++){
            Random random = new Random();
            try {
                Thread.sleep(random.nextInt(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            service.submit(work);
        }

    }

    static class Work implements Runnable{


        @Override
        public void run() {
            if(canAccess()){
                System.out.println("可以访问！");
            }else{
                System.out.println("你被限制住了！");
            }
        }
    }
}
