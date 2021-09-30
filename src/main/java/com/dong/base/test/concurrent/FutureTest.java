package com.dong.base.test.concurrent;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/9/14
 */
public class FutureTest {

    public static void main(String[] args) {
        ExecutorService executor = new ThreadPoolExecutor(3,5,6,TimeUnit.SECONDS,new LinkedBlockingQueue<>(100));
        OddNumber oddNumber = new OddNumber();
        Future<Integer> future = executor.submit(oddNumber);
        long startTime = System.currentTimeMillis();
        int evenNumber = 2 + 4 + 6 + 8 + 10;
        try {
            Thread.sleep(1000);
            System.out.println("0.开始了：" + (System.currentTimeMillis() - startTime) + "秒");
            int oddNumberResult = future.get();//这时间会被阻塞
            System.out.println("1+2+...+9+10=" + (evenNumber + oddNumberResult));
            System.out.println("1.开始了：" + (System.currentTimeMillis() - startTime) + "秒");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static class OddNumber implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            Thread.sleep(20000);
            int result = 1 + 3 + 5 + 7 + 9;
            return result;
        }

    }
}
