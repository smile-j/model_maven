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
                    4, 8, 0, TimeUnit.MINUTES, new LinkedBlockingQueue<>(3000), threadFactory);
    ListeningExecutorService executorService =
            MoreExecutors.listeningDecorator(threadPoolExecutor); // 第三方可接受最大并发数为4

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
                    throw new Exception("err");
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
        List<String> list = Arrays.asList("AA", "BB", "CC");
        list.stream().forEach((e) -> {
            Future<String> submit = threadPoolExecutor.submit(() -> {
                Random random = new Random();
                if ("BB".equals(e)) {
//                    throw  new Exception("err");
                    TimeUnit.SECONDS.sleep(random.nextInt(10));
                }
//                TimeUnit.SECONDS.sleep(random.nextInt(1000) + 1000);
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
}