package com.dong.base.test.design;

import org.junit.Test;

/**
 * Created by Administrator on 2018/6/13.
 */
public class TestDesign {

//    @Test
//    public void testSignleton(){
    public static void main(String[] args){
           for (int i=0;i<100;i++){
               final int num = i;
               new Thread(new Runnable() {
                   @Override
                   public void run() {
                       System.out.println(Thread.currentThread().getName()+"-------------"+(num+1)+"     "+2);
                       System.out.println("result: "+Thread.currentThread().getName()+"   "+Singleton.getInstance().add(num+1,2));
                   }
               },i+"").start();
           }

    }

}
