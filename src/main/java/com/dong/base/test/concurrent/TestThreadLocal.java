package com.dong.base.test.concurrent;

import org.junit.Test;


/**
 *
 * ThreadLocal   弱引用  WeakReference
 *
 *
 */
public class TestThreadLocal {

   public static void main(String [] args){

        new Thread("Thread1"){
            @Override
            public void run(){
                update();
            }
        }.start();

       new Thread("Thread2"){
           @Override
           public void run(){
               update();
           }
       }.start();

       new Thread("Thread3"){
           @Override
           public void run(){
               update();
           }
       }.start();

       new Thread("Thread4"){
           @Override
           public void run(){
               update();
           }
       }.start();

   }

   private static ThreadLocal<Integer> initValue = new ThreadLocal<Integer>(){
       @Override
       public Integer initialValue(){
           return 10;
       }
   };
   public static void update(){
       initValue.set(initValue.get()+66);
       System.out.println(initValue.get());
   }
   public static void remove(){
       initValue.remove();
   }

}
