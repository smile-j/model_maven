package com.dong.base.test.concurrent;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/2/5.
 */
public class LocalCacheTest implements Runnable {

    @Override
    public void run() {
        LocalCache.isAccess("127.0.0.1", 3000L, 3);
    }
    static Vector<Integer> vector = new Vector<Integer>();

   public static void main(String[] args) {
       ExecutorService pool = Executors.newCachedThreadPool();
       LocalCacheTest test = new LocalCacheTest();
       for (int i = 0; i < 100; i++) {
           pool.execute(test);
       }
       pool.shutdown();
       try {
           pool.awaitTermination(10, TimeUnit.SECONDS);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
   }

      /*  public static void main(String[] args) throws InterruptedException {
            while(true) {
                for(int i=0;i<10;i++)
                    vector.add(i);
                Thread thread1 = new Thread(){
                    public void run() {
                        for(int i=0;i<vector.size();i++)
                            vector.remove(i);
                    };
                };
                Thread thread2 = new Thread(){
                    public void run() {
                        for(int i=0;i<vector.size();i++)
                            vector.get(i);
                    };
                };
                thread1.start();
                thread2.start();
                while(Thread.activeCount()>10)   {

                }
            }
        }
*/


}
