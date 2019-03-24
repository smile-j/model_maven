package com.dong.base.test.thread;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2017/11/20.
 */
public class TestThread {

    @Test
    public void  test1(){
//    public static void main(String [] args){
        ExecutorService pool = Executors.newFixedThreadPool(15);//Executors.defaultThreadFactory()
        List<Future<Long>> results = new ArrayList<Future<Long>>();
        MyCallable myCallable = new MyCallable();
        for(int i=0;i<12;i++ ){
            results.add(pool.submit(myCallable));
        }
        pool.shutdown();
        for(Future future:results){
            try {
                Long times = (Long) future.get();
                Date date = new Date(times);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                System.out.println("  "+ simpleDateFormat.format(date));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void   test2(){
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        final List<String> list = new ArrayList<String>();
        for(int i=0;i<11;i++){
            executorService.execute(new Runnable() {
                public void run() {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date nowDate = new Date();
                    synchronized (list){
                        list.add(simpleDateFormat.format(nowDate));
                    }
                    System.out.println("Asynchronous task:"+simpleDateFormat.format(nowDate));
                }
            });

        }
        while (list.size()<11){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("end ....."+list.size());
        executorService.shutdown();
    }

    @Test
    public void test3(){
        ExecutorService pool = Executors.newFixedThreadPool(1);

        /*
          ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
          ExecutorService fixedThreadPool = Executors.newCachedThreadPool();
          fixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {

                }
                                  });
         fixedThreadPool.shutdown();
        fixedThreadPool.awaitTermination(1, TimeUnit.DAYS);
        */

      /*  ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.schedule(new Runnable() {

            @Override
            public void run() {
                System.out.println("delay 3 seconds");
            }
        }, 3, TimeUnit.SECONDS);*/

        List<Future<Object>> results = new ArrayList<Future<Object>>();
        MyCallable myCallable = new MyCallable();
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(int i=0;i<12;i++ ){

            results.add((Future<Object>) pool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("one:"+simpleDateFormat.format(new Date()));
//                    return simpleDateFormat.format(new Date());
                }
            }));

//            results.add(pool.submit(new Callable<Object>() {
//                @Override
//                public Object call() throws Exception {
//                    return simpleDateFormat.format(new Date());
//                }
//            }));

        }
        pool.shutdown();
        for(Future future:results){
            try {
                System.out.println(results.size()+"  "+ future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
    }


    @Test
    public void test5(){

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Set<Callable<String>> callables = new HashSet<Callable<String>>();

        callables.add(new Callable<String>() {
            public String call() throws Exception {
                System.out.println("1");
                return "Task 1";
            }
        });
        callables.add(new Callable<String>() {
            public String call() throws Exception {
                System.out.println("2");
                return "Task 2";
            }
        });
        callables.add(new Callable<String>() {
            public String call() throws Exception {
                System.out.println("3");
                return "Task 3";
            }
        });

        List<Future<String>> result = null;
        try {
            result = executorService.invokeAll(callables);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

      Iterator<Future<String>> iterator = result.iterator();
//        while (iterator.hasNext()){
//            try {
//                System.out.println(iterator.next().get());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//        }

        executorService.shutdown();
    }

    @Test
    public  void test6(){
       long curDateTimes = System.currentTimeMillis();
        ExecutorService pool =  Executors.newCachedThreadPool();
        for(int i=0;i<100;i++){
            final  int num = i;
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    int sum=0;
                    for(int j=0;j<500;j++){
                        try {
                            if(num>3)
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        sum+=j;
                    }
                System.out.println(Thread.currentThread().getName()+" :"+sum);
                }
            });
        }
        pool.shutdown();
        try {
//            System.out.println(11);
//            pool.awaitTermination(10,TimeUnit.SECONDS);
//            System.out.println(11);
        while (!pool.awaitTermination(1, TimeUnit.SECONDS)) {
            System.out.println("线程池没有关闭");
        }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程池已经关闭");
//        pool.shutdown();
        System.out.println("耗时："+(System.currentTimeMillis()-curDateTimes));
    }

    @Test
    public void test7(){
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        for(int i=0;i<10;i++){
            final int num = i;
            scheduledThreadPool.schedule(new Runnable() {

                @Override
                public void run() {
                    System.out.println("delay 3 seconds"+num);
                }
            }, 3, TimeUnit.SECONDS);

        }

    }

    @Test
    public void test(){

        DemoCall td = new DemoCall();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(td);
        new Thread(futureTask).start();
        try {
            System.out.println("    "+futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
class DemoCall implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i=0;i<=100;i++){
            sum+=i;
        }
        return sum;
    }
}
