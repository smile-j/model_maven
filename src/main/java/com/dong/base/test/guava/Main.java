package com.dong.base.test.guava;

import com.google.common.util.concurrent.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Main {


    ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("async-pool-%d").build();

    ThreadPoolExecutor threadPoolExecutor =
            new ThreadPoolExecutor(
                    2, 2, 0, TimeUnit.MINUTES, new LinkedBlockingQueue<>(3), threadFactory);
    ListeningExecutorService executorService =
            MoreExecutors.listeningDecorator(threadPoolExecutor); // 第三方可接受最大并发数为4
    ExecutorService pool = new ThreadPoolExecutor(
            2, 2, 0, TimeUnit.MINUTES, new LinkedBlockingQueue<>(3), threadFactory);
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ".....start");

        Main main = new Main();
        main.testThread();
        System.out.println(Thread.currentThread().getName() + ".....end");


    }

    public void testGuavaThread() {

        List<String> list = Arrays.asList("AA", "BB", "CC");
        list.stream().forEach(e -> {

            ListenableFuture<String> futureTask = executorService.submit(() -> {
                if ("BB".equals(e)) {
//                    throw new Exception("err");
                    int a = 1/0;
                }
                System.out.println("--- " + e);
                return e;
            });

            Futures.addCallback(futureTask, new FutureCallback<String>() {
                @Override
                public void onSuccess(String o) {
                    System.out.println(Thread.currentThread().getName() + "---success---" + o);
                }

                @Override
                public void onFailure(Throwable throwable) {
                    System.out.println(Thread.currentThread().getName() + "---error---" + throwable.getLocalizedMessage());
                }
            });

        });
        executorService.shutdown();

    }

    public void testThread() {
        List<String> list = Arrays.asList("AA", "BB", "CC","DD","EE","FF","GG","GG1","GG2","GG3","GG4","GG5","GG6","GG7","GG8","GG9");
        list.stream().forEach((e) -> {
            Future<String> submit = threadPoolExecutor.submit(() -> {
                Random random = new Random();
                if ("BB".equals(e)) {
//                    throw  new Exception("err");
//                    TimeUnit.SECONDS.sleep(random.nextInt(10));
                }
                TimeUnit.SECONDS.sleep(random.nextInt(10));
                System.out.println(Thread.currentThread().getName() + "--- " + e);
                return e;
            });
            try {
                System.out.println(Thread.currentThread().getName() + "---success---" + submit.get());
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            } catch (ExecutionException executionException) {
                executionException.printStackTrace();
            }

        });
        threadPoolExecutor.shutdown();


    }

    public static void test(){
        ConcurrentHashMap concurrenthashmap = new ConcurrentHashMap<String,String>();
        concurrenthashmap.put("","");
        CompletableFuture future1 = CompletableFuture.runAsync(()->{
        });
        future1 = CompletableFuture.supplyAsync(()->{return "";});
        //感知
//        future.whenComplete((res,ex)->{
//
//        }).exceptionally(exception->{
//            System.out.println(exception);
//        });
        //方法执行完成后的处理
//        future.handle((re,ex)->{
//            return "";
//        });
        //1.没有返回值，不能用上一步的执行结果
        future1.thenRun(()->{});
        future1.thenRunAsync(()->{});
        //2.没有返回值，能用上一步的执行结果
        future1.thenAccept(future->{

        });
        future1.thenAcceptAsync(future->{

        });
        //3.有返回值，能用上一步的执行结果
        CompletableFuture future2 = future1.thenApply(future -> {
            return "";
        });
    }
}