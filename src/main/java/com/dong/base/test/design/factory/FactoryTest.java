package com.dong.base.test.design.factory;

import org.junit.Test;

import java.util.HashMap;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/1/2.
 */
public class FactoryTest {

    @Test
    public void test1(){

    }

    public static void main(String[] args) throws InterruptedException {

        FutureTask<String> futureTask = new FutureTask<>(() -> {
            System.out.println("..start");
//            int a = 1 / 0;
            System.out.println("... end");
           int aa=11;
           if(aa>10){
               throw new RuntimeException();
           }
            return "OK";
        });
        new Thread(futureTask).start();
        try {
            System.out.println(futureTask.get());
        }catch (Exception e){
            e.printStackTrace();
        }

        new Thread(()->{
            System.out.println("..start");
            int a = 1/0;
            int aa=11;
            if(aa>10){
                throw new RuntimeException();
            }
            System.out.println("... end");
        }).start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("main... end");

    }
}
