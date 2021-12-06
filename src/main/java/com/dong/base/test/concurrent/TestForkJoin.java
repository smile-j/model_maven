package com.dong.base.test.concurrent;

import java.util.concurrent.*;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/12/6
 */
public class TestForkJoin {



    public static void main(String[] args) {
        /**
         * 普通线程池和forkJoin测试
         *
         *  普通线程池 2个 死锁；forkJoin可以完成执行 原因：
         *    普通线程池所有线程共享一个工作队列，有空闲线程时工作队列中的任务才能得到执行
         *    ForkJoinPool 中的每个线程有自己独立的工作队列，每个工作线程运行中产生新的任务，放在队尾
         *    某个工作线程会尝试窃取别个工作线程队列中的任务，从队列头部窃取
         *    遇到 join() 时，如前面的 future.get()，如果 join 的任务尚未完成，则可先处理其他任务
         */
//        testThreadPool(Executors.newFixedThreadPool(2));
        /**
         * 或者
         * testThreadPool(Executors.newWorkStealingPool(2));
         * testThreadPool(ForkJoinPool.commonPool()); 线程池大小由机器的 CPU 内核决定的
         */
        testThreadPool(new ForkJoinPool(2));




        //测试forkjoin
        //testForkJoin();
    }

    private static void testThreadPool(ExecutorService threadPool) {
        Future[] outerTasks = IntStream.rangeClosed(1, 2).mapToObj(i ->
                threadPool.submit(() -> {
                    System.out.println(Thread.currentThread().getName() + ", level1 task " + i);

                    Future<?> innerTask = threadPool.submit(() ->
                            System.out.println(Thread.currentThread().getName() + ", level2 task" + i));

                    try {
                        innerTask.get();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })).toArray(Future[]::new);

        System.out.println("waiting...");
        try {
            for (Future<?> outerTask : outerTasks) {
                outerTask.get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("done");
    }

    public static void testForkJoin(){
        long[] numbers = LongStream.rangeClosed(1, 100000L).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        Long result = new ForkJoinPool().invoke(task);
        System.out.printf("Final result: %s, CPU cores: %s\n", result,
                Runtime.getRuntime().availableProcessors());
    }

    static class ForkJoinSumCalculator extends RecursiveTask<Long> {

        private final long[] numbers;
        private final int start;
        private final int end;

        public static final long THRESHOLD = 10_000L;

        public ForkJoinSumCalculator(long[] numbers) {
            this(numbers, 0, numbers.length);
        }

        private ForkJoinSumCalculator(long[] numbers, int start, int end) {
            this.numbers = numbers;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            int length = end - start;
            if (length <= THRESHOLD) {
                return computeSequentially();
            }

            //fork schedules task on new thread, compute reuses the same thread
//    return new ForkJoinSumCalculator(numbers, start, start + length / 2).fork().join()
//           + new ForkJoinSumCalculator(numbers, start + length / 2, end).compute();

            ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
            leftTask.fork();

            ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);

            Long rightResult = rightTask.compute();
            Long leftResult = leftTask.join();

            return leftResult + rightResult;
        }

        private long computeSequentially() {
            System.out.printf("Summation from %s to %s, calculated by thread %s\n", start, (end - 1), Thread.currentThread().getName());
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += numbers[i];
            }
            return sum;
        }
    }
}
