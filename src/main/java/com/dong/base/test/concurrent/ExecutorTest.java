package com.dong.base.test.concurrent;

import com.google.common.collect.Lists;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ExecutorTest {

    public static void main(String[] args) throws Exception {
        test3();
    }

    public static void test1() throws InterruptedException, ExecutionException{
        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorCompletionService<String> completionService = new ExecutorCompletionService<>(executorService);
        System.out.println("公司让你通知大家聚餐 你开车去接人");
        completionService.submit(() -> {
            System.out.println("总裁：我在家上大号 我最近拉肚子比较慢 要蹲1个小时才能出来 你等会来接我吧");
            TimeUnit.SECONDS.sleep(10);
            System.out.println("总裁：1小时了 我上完大号了。你来接吧");
            return "总裁上完大号了";
        });
        completionService.submit(() -> {
            System.out.println("研发：我在家上大号 我比较快 要蹲3分钟就可以出来 你等会来接我吧");
            TimeUnit.SECONDS.sleep(3);
            System.out.println("研发：3分钟 我上完大号了。你来接吧");
            return "研发上完大号了";
        });
        completionService.submit(() -> {
            System.out.println("中层管理：我在家上大号  要蹲10分钟就可以出来 你等会来接我吧");
            TimeUnit.SECONDS.sleep(6);
            System.out.println("中层管理：10分钟 我上完大号了。你来接吧");
            return "中层管理上完大号了";
        });
        TimeUnit.SECONDS.sleep(1);
        System.out.println("都通知完了,等着接吧。");
        //提交了3个异步任务）
        for (int i = 0; i < 3; i++) {
            String returnStr = completionService.take().get();
            System.out.println(returnStr + "，你去接他");
        }
        Thread.currentThread().join();
    }
    public static void test2() throws InterruptedException, ExecutionException{
//        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        System.out.println("公司让你通知大家聚餐 你开车去接人");
        List<Future<String>> res = new LinkedList<>();
       res.add(executorService.submit(() -> {
            System.out.println("总裁：我在家上大号 我最近拉肚子比较慢 要蹲1个小时才能出来 你等会来接我吧");
            TimeUnit.SECONDS.sleep(10);
            System.out.println("总裁：1小时了 我上完大号了。你来接吧");
            return "总裁上完大号了";
        }));
        res.add(executorService.submit(() -> {
            System.out.println("研发：我在家上大号 我比较快 要蹲3分钟就可以出来 你等会来接我吧");
            TimeUnit.SECONDS.sleep(3);
            System.out.println("研发：3分钟 我上完大号了。你来接吧");
            return "研发上完大号了";
        }));
        res.add(executorService.submit(() -> {
            System.out.println("中层管理：我在家上大号  要蹲10分钟就可以出来 你等会来接我吧");
            TimeUnit.SECONDS.sleep(6);
            System.out.println("中层管理：10分钟 我上完大号了。你来接吧");
            return "中层管理上完大号了";
        }));
        TimeUnit.SECONDS.sleep(1);
        System.out.println("都通知完了,等着接吧。");
        //提交了3个异步任务）

        /**
         * 有问题
         * 第一个人结束，才会循环接了下来的内容
         */
//        for (int i = 0; i < 3; i++) {
//            String returnStr = res.get(i).get();
//            System.out.println(returnStr + "，你去接他");
//        }
        Thread.currentThread().join();
    }

    public static void test3() throws Exception{
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("总裁：我在家上大号 我最近拉肚子比较慢 要蹲1个小时才能出来 你等会来接我吧");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("总裁：1小时了 我上完大号了。你来接吧");
            return "总裁上完大号了";
        });
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("研发：我在家上大号 我比较快 要蹲3分钟就可以出来 你等会来接我吧");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("研发：3分钟 我上完大号了。你来接吧");
            return "研发上完大号了";
        });
        CompletableFuture<String> f3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("中层管理：我在家上大号  要蹲10分钟就可以出来 你等会来接我吧");
            try {
                TimeUnit.SECONDS.sleep(6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("中层管理：10分钟 我上完大号了。你来接吧");
            return "中层管理上完大号了";
        });
//        List< CompletableFuture<String>> res = Lists.newArrayList(f1,f2,f3);
//        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(f1, f2, f3);
//        CompletableFuture<List<String>> listCompletableFuture = voidCompletableFuture.thenApply(e -> res.stream().map(f -> f.join()).collect(Collectors.toList()));
//        System.out.println(listCompletableFuture.get());
        CompletableFuture<Object> objectCompletableFuture = CompletableFuture.anyOf(f1, f2, f3);
        System.out.println(objectCompletableFuture.get());
    }

}
