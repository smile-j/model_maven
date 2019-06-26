package com.dong.base.jdk8.parallel;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * Created by Administrator on 2017/11/16.
 */
public class TestForkJoin {

    /**ForkJoin
     *
     */
    @Test
    public void test1(){
        Instant start = Instant.now();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCalculate(0,10000L);

        Long sum = pool.invoke(task);
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println("耗费时间:" + Duration.between(start,end).toMillis());

    }

    @Test
    public void test2(){
        Instant start = Instant.now();

        long sum =0;

        for(long i=0;i<=100L;i++){
            sum+=i;
        }
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println("耗费时间:" + Duration.between(start,end).toMillis());
    }

    /**
     * java 8并行流
     */
    @Test
    public void test3(){
        Instant start = Instant.now();
        long sum =  LongStream.rangeClosed(0,10000000L)
                    .parallel()//sequential() 并行流和串行流切换
                .reduce(0,Long::sum);
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println("耗费时间:" + Duration.between(start,end).toMillis());
    }
}
