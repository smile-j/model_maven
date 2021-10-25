package com.dong.base.test.concurrent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/10/15
 */
public class TestCompletableFuture3 {

    public static void main(String[] args) throws InterruptedException {
        test3();
    }

    public static void test3() throws InterruptedException {
        Thread carOne = new Thread(() -> {
            long startMills = System.currentTimeMillis();
            while (System.currentTimeMillis() - startMills < 3) {
//                if (Thread.currentThread().isInterrupted()) {
                    if (Thread.interrupted()) {//检查中断并且恢复原状态
                        System.out.println("向左开1米");
                    }else {
                            System.out.println("往前开1米");
                    }
                };
        });
        carOne .start();
       TimeUnit.MILLISECONDS.sleep(1);
        carOne.interrupt();
    }


    public static void test2(){

        CompletableFuture [] futures = new CompletableFuture[8];
        System.out.println("....end"+System.currentTimeMillis());
        for (int i=0;i<8;i++){
            CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return System.currentTimeMillis();
            });
            futures[i]= future;
        }
        CompletableFuture.allOf(futures).join();
        System.out.println("....end"+System.currentTimeMillis());


    }

    public static void test1(){

        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(ForkJoinPool.commonPool().getPoolSize());
        System.out.println(ForkJoinPool.getCommonPoolParallelism());

    }

}
