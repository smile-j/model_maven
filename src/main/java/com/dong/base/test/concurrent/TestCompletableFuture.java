package com.dong.base.test.concurrent;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.*;

/**
 * @author dongjunpeng
 * @Description CompletableFuture基本使用
 * @date 2021/9/13
 */
public class TestCompletableFuture {

    public static Executor poolExecutor = new ThreadPoolExecutor(3,5,5,TimeUnit.SECONDS,new LinkedBlockingQueue<>(100));

    public static void main(String[] args) throws Exception {
//        thenCompose();
//        runAfterBoth();
//        runAfterEither();
//        applyToEither();
//        thenAcceptBoth();
//        thenRun();
//        thenAccept();
//        handle();
//        thenApply();
//        whenComplete();
//        System.out.println(supplyAsync());
//        System.out.println(runAsync());
        TimeUnit.SECONDS.sleep(2);

    }

    /**
     * 14.thenCompose
     * thenCompose 方法允许你对两个 CompletionStage 进行流水线操作，第一个操作完成时，将其结果作为参数传递给第二个操作
     * public <U> CompletableFuture<U> thenCompose(Function<? super T, ? extends CompletionStage<U>> fn);
     * public <U> CompletableFuture<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn) ;
     * public <U> CompletableFuture<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn, Executor executor) ;
     */
    public static void thenCompose() throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(3);
                try {
                    TimeUnit.SECONDS.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t1="+t);
                return t;
            }
        }).thenCompose(new Function<Integer, CompletionStage<Integer>>() {
            @Override
            public CompletionStage<Integer> apply(Integer integer) {
              return CompletableFuture.supplyAsync(new Supplier<Integer>() {
                  @Override
                  public Integer get() {
                      int t = integer *2;
                      System.out.println("t2="+t);
                      return t;
                  }
              });
            }
        });
        System.out.println(f1.get());
    }

    /**
     * 13.runAfterBoth
     * 两个CompletionStage，都完成了计算才会执行下一步的操作（Runnable）
     * public CompletionStage<Void> runAfterBoth(CompletionStage<?> other,Runnable action);
     * public CompletionStage<Void> runAfterBothAsync(CompletionStage<?> other,Runnable action);
     * public CompletionStage<Void> runAfterBothAsync(CompletionStage<?> other,Runnable action,Executor executor);
     *
     */
    public static void runAfterBoth(){

        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(3);
                try {
                    TimeUnit.SECONDS.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("f1="+t);
                return t;
            }
        });
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(3);
                try {
                    TimeUnit.SECONDS.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("f2=" + t);
                return t;
            }
        });
        f1.runAfterBoth(f2, new Runnable() {
            @Override
            public void run() {
                System.out.println("俩个任务都执行完了！！！");
            }
        });

    }

    /**
     * 12. runAfterEither 方法
     *  两个CompletionStage，任何一个完成了都会执行下一步的操作（Runnable）
     * public CompletionStage<Void> runAfterEither(CompletionStage<?> other,Runnable action);
     * public CompletionStage<Void> runAfterEitherAsync(CompletionStage<?> other,Runnable action);
     * public CompletionStage<Void> runAfterEitherAsync(CompletionStage<?> other,Runnable action,Executor executor);
     */
    public static void runAfterEither(){

        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(3);
                try {
                    TimeUnit.SECONDS.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("f1=" + t);
                return t;
            }
        });
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(3);
                try {
                    TimeUnit.SECONDS.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("f2=" + t);
                return t;
            }
        });
        f1.runAfterEither(f2, new Runnable() {
            @Override
            public void run() {
                System.out.println("f1 f2 其中有一个已经完成！！！");
            }
        });

    }

    /**
     * 11. acceptEither 无返回值
     * 两个CompletionStage，谁执行返回的结果快，我就用那个CompletionStage的结果进行下一步的消耗操作。
     * public CompletionStage<Void> acceptEither(CompletionStage<? extends T> other,Consumer<? super T> action);
     * public CompletionStage<Void> acceptEitherAsync(CompletionStage<? extends T> other,Consumer<? super T> action);
     * public CompletionStage<Void> acceptEitherAsync(CompletionStage<? extends T> other,Consumer<? super T> action,Executor executor);
     *
     */
    private static void acceptEither() throws Exception {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(3);
                try {
                    TimeUnit.SECONDS.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("f1=" + t);
                return t;
            }
        });
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(3);
                try {
                    TimeUnit.SECONDS.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("f2=" + t);
                return t;
            }
        });
        f1.acceptEither(f2, new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println("last:"+integer);
            }
        });
    }

        /**
         * 10 applyToEither 方法  有返回值
         *  两个CompletionStage，谁执行返回的结果快，就用那个CompletionStage的结果进行下一步的转化操作。
         * public <U> CompletionStage<U> applyToEither(CompletionStage<? extends T> other,Function<? super T, U> fn);
         * public <U> CompletionStage<U> applyToEitherAsync(CompletionStage<? extends T> other,Function<? super T, U> fn);
         * public <U> CompletionStage<U> applyToEitherAsync(CompletionStage<? extends T> other,Function<? super T, U> fn,Executor executor);
         *
         */
    public static void applyToEither()throws Exception{

        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(3);
                try {
                    TimeUnit.SECONDS.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("f1=" + t);
                return t;
            }
        });
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(3);
                try {
                    TimeUnit.SECONDS.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("f2=" + t);
                return t;
            }
        });
        CompletableFuture<Integer> result = f1.applyToEither(f2, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                System.out.println("last:" + integer);
                return integer * 2;
            }
        });
        System.out.println("result:"+result.get());
    }

    /**
     * 9. thenAcceptBoth
     * 当两个CompletionStage都执行完成后，把结果一块交给thenAcceptBoth来进行消耗
     * public <U> CompletionStage<Void> thenAcceptBoth(CompletionStage<? extends U> other,BiConsumer<? super T, ? super U> action);
     * public <U> CompletionStage<Void> thenAcceptBothAsync(CompletionStage<? extends U> other,BiConsumer<? super T, ? super U> action);
     * public <U> CompletionStage<Void> thenAcceptBothAsync(CompletionStage<? extends U> other,BiConsumer<? super T, ? super U> action,     Executor executor);
     */
    public static void thenAcceptBoth() throws Exception{
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(3);
                try {
                    TimeUnit.SECONDS.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("f1=" + t);
                return t;
            }
        });
        final CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(3);
                try {
                    TimeUnit.SECONDS.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("f2=" + t);
                return t;
            }
        });
        f1.thenAcceptBoth(f2, new BiConsumer<Integer, Integer>() {
            @Override
            public void accept(Integer integer, Integer integer2) {
                System.out.println("f1="+integer+";f2="+integer2);
            }
        });
        TimeUnit.SECONDS.sleep(3);
    }

    /**
     * 8.thenCombine 合并任务
     *  henCombine 会把 两个 CompletionStage 的任务都执行完成后，把两个任务的结果一块交给 thenCombine 来处理
     * public <U,V> CompletionStage<V> thenCombine(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn);
     * public <U,V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn);
     * public <U,V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn,Executor executor);
     *
     */
    public static void thenCombine() throws ExecutionException, InterruptedException {

        CompletableFuture<String> future1= CompletableFuture.supplyAsync(() -> {
            return "hello";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            return "world !";
        });
        CompletableFuture<String> result = future1.thenCombine(future2, new BiFunction<String, String, String>() {

            @Override
            public String apply(String s1, String s2) {
                return s1 + " " + s2;
            }
        });
        System.out.println(result.get());


     /*   CompletableFuture<String> combine = CompletableFuture.supplyAsync(() -> {
            return "hello";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            return "world !";
        }), (f1, f2) -> {
            return f1 + " " + f2;
        });*/
    }

    /**
     * 7. thenRun
     *  跟 thenAccept 方法不一样的是，不关心任务的处理结果。只要上面的任务执行完成，就开始执行 thenAccept 。
     *  public CompletionStage<Void> thenRun(Runnable action);
     *  public CompletionStage<Void> thenRunAsync(Runnable action);
     *  public CompletionStage<Void> thenRunAsync(Runnable action,Executor executor);
     *
     */
    public static void thenRun() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            int a = 1/0;
            return new Random().nextInt(10);
        }).thenRun(() -> {
            System.out.println("......end");
        });
        System.out.println(future.get());
    }
    /**
     * 6. thenAccept
     * 接收任务的处理结果，并消费处理，无返回结果
     * public CompletionStage<Void> thenAccept(Consumer<? super T> action);
     * public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action);
     * public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action,Executor executor);
     *
     */
    public static void thenAccept() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            int a = 1/0;
            return new Random().nextInt(10);
        }).thenAccept(integer -> {

            System.out.println("result:" + integer);
        });
        System.out.println(future.get());
    }

    /**
     * 5.handle
     * 执行任务完成时对结果的处理
     * handle方法和thenApply方法处理方式基本一样，
     * 不同的是handle是在任务完成后再执行，还可以处理异常任务；thenApply只可以执行正常的任务，任务出现异常则不执行thenApply方法
     * public <U> CompletionStage<U> handle(BiFunction<? super T, Throwable, ? extends U> fn);
     * public <U> CompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn);
     * public <U> CompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn,Executor executor);
     *
     */
    public static void handle() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> handle = CompletableFuture.supplyAsync(() -> {
            int i = 10 / 0;
            return new Random().nextInt(10);
        }).handle((Integer param, Throwable t) -> {
            int result = -1;
            if (t == null) {
                result = param * 2;
            } else {
                System.out.println("异常信息：" + t.getMessage());
            }
            return result;
        });
        System.out.println(handle.get());

    }

    /**
     * 4. thenApply 方法 不能处理异常的结果
     * 当一个线程依赖另一个线程时，可以使用 thenApply 方法来把这两个线程串行化
     * public <U> CompletableFuture<U> thenApply(Function<? super T,? extends U> fn)
     * public <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn)
     * public <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)
     *
     * Function<? super T,? extends U>
     * T：上一个任务返回结果的类型
     * U：当前任务的返回值类型
     *
     */
    @Test
    public  void thenApply()throws Exception{

        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<Long>() {
            @Override
            public Long get() {
                long result = new Random().nextInt(100);
//                int qq =1/0;
                System.out.println("result:"+result);
                return result;
            }
        }).thenApply(new Function<Long, String>() {
            @Override
            public String apply(Long aLong) {
                long result = aLong*5;
                System.out.println("result2:"+result);
                return result+"--haha";
            }
        });
        String s = future.get();
        System.out.println("last:"+s);

    }

    /**
     * 3. whenComplete
     * 计算结果完成时的回调方法 或者抛出异常的时候，可以执行特定的Action
     *  public CompletableFuture<T> whenComplete(BiConsumer<? super T,? super Throwable> action)
     *  public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T,? super Throwable> action) 异步
     *  public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T,? super Throwable> action, Executor executor)
     *  public CompletableFuture<T> exceptionally(Function<Throwable,? extends T> fn)
     *
     */
    @Test
    public  void whenComplete()throws Exception{
        CompletableFuture<Void> future = CompletableFuture.runAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(new Random().nextInt()%2==0){
                int i = 12/0;
            }
            System.out.println(Thread.currentThread().getName()+" run end ....");

        },poolExecutor);
        future.whenComplete(new BiConsumer<Void, Throwable>() {
            @Override
            public void accept(Void unused, Throwable throwable) {
                System.out.println(Thread.currentThread().getName()+" finally....执行完成！");
            }
        }).exceptionally(new Function<Throwable, Void>() {
            @Override
            public Void apply(Throwable throwable) {
                System.out.println(Thread.currentThread().getName()+" 执行失败！ "+throwable.getMessage());
                return null;
            }
        });
        TimeUnit.SECONDS.sleep(3);

/*        CompletableFuture.supplyAsync(()->{
            int radNum = new Random().nextInt();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(radNum%2==0){
                int i = 12/0;
            }
            System.out.println("run end ....");
            return radNum;
        }).whenComplete((integer,throwable)->{
            System.out.println("finally....执行完成！"+integer+throwable);
        }).exceptionally(throwable->{
            System.out.println("执行失败！ "+throwable.getMessage());
            return null;
        });*/

    }


    /**
     * 1. 有返回值
     *
     * supplyAsync(Supplier<U> supplier)
     *  supplyAsync(Supplier<U> supplier, Executor executor)
     */

    @Test
    public void supplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"等待2秒");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return System.currentTimeMillis();
        });
        //future.join()
        System.out.println(Thread.currentThread().getName()+"....end " + future.get());
    }

    /**
     *  2. 无返回值
     *      * runAsync(Runnable runnable)
     *      *  runAsync(Runnable runnable, Executor executor)
     */
    @Test
    public  void runAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(".....run end.");
        });
//        return future.get();
        System.out.println(future.get());
    }

}
