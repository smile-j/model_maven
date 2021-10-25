package com.dong.base.test.concurrent;

import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/10/9
 */
public class TestCompletableFuture2 {

    public static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3, 3, 5L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1000));


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test5();
//        test4();
//        test3();
//        test2();
//        test1();
    }

    public static void test5(){
        System.out.println(Thread.currentThread().getName()+"...等车回家700或者800");
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "700路";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "800路";
        }), (fistrtBus) -> {
            if (fistrtBus.equals("700路")){
                throw new RuntimeException("撞树了");
            }
            return fistrtBus;
        }).exceptionally(new Function<Throwable, String>() {
            @Override
            public String apply(Throwable throwable) {
                System.out.println(Thread.currentThread().getName()+"呼叫出租车");
                return "出租车";
            }
        });

        System.out.println(Thread.currentThread().getName()+"乘坐。。"+future.join());
    }
    /**
     * 张 三： 付钱            回家
     * 收钱的：     收到钱
     * 开票的：          开发票
     *
     */
    public static void test4(){
        System.out.println(Thread.currentThread().getName()+"...吃完饭");
        System.out.println(Thread.currentThread().getName()+"...付钱");
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "...收到钱200");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "200";
        }).thenApplyAsync((moneyNum) -> {
            System.out.println(Thread.currentThread().getName() + "...开" + moneyNum + "的发票");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return moneyNum + "的发票";
        });

        System.out.println(Thread.currentThread().getName()+" 拿到 "+future.join()+"...回家");


    }

    /**
     * 张三进餐厅 点餐、打游戏    吃饭
     * 厨师           做菜
     * 服务员         做饭、打饭
     */
    public static void test3(){
        System.out.println(Thread.currentThread().getName()+"...点餐");

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "...下厨...");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "西红柿炒鸡蛋";
        }).thenCombine(CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName() + "...做饭");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "-大米";
        }).thenApply((s)->{
            System.out.println(Thread.currentThread().getName() + "...盛"+s);
            return "-"+s;
        }) ,((s1, s2) ->{
            return s1+"-"+s2;
        }) );
        System.out.println(Thread.currentThread().getName()+"...打游戏");
        System.out.println(Thread.currentThread().getName()+"...吃饭 "+ future1.join());
    }

    /**
     * 张三进餐厅 点餐、打游戏、   吃饭
     * 厨师           做菜
     * 服务员              打饭
     */
    public static void test2(){
        System.out.println(Thread.currentThread().getName()+"...点餐");
        System.out.println(Thread.currentThread().getName()+"...打游戏");
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "...下厨...");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "西红柿炒鸡蛋";
        },poolExecutor).thenCompose(new Function<String, CompletionStage<String>>() {
            @Override
            public CompletionStage<String> apply(String s) {
                return CompletableFuture.supplyAsync(new Supplier<String>() {
                    @Override
                    public String get() {
                        System.out.println(Thread.currentThread().getName() + "...盛大米");
                        return s + "--大米";
                    }
                },poolExecutor);
            }
        });
        System.out.println(Thread.currentThread().getName()+"...吃饭 "+future.join());

    }
    /**
     * 张三进餐厅 点餐、打游戏、吃饭
     * 厨师           下厨
     */
    public static void test1() throws ExecutionException, InterruptedException {
        System.out.println(Thread.currentThread().getName()+"...点餐");
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+"...下厨...");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "蛋炒饭";
        },poolExecutor);
        System.out.println(Thread.currentThread().getName()+"...打游戏");
        System.out.println(Thread.currentThread().getName()+"...吃饭 "+future.get());
    }

}
