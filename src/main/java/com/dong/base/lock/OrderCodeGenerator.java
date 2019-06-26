package com.dong.base.lock;

import com.dong.base.lock.redis.DistributedLock;
import com.dong.base.lock.zk.ZookeeperImproveLock;
import org.junit.Test;
import xyz.downgoon.snowflake.Snowflake;
import xyz.downgoon.snowflake.util.BinHexUtil;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2018/3/6.
 */
public class OrderCodeGenerator {

    private static int i=0;
    private int num =100;
    static Lock lock = new ReentrantLock();
    private static  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

//    public synchronized static String getOrderCode() {
    public  static String getOrderCode() {
//        lock.lock();
        try {
            return simpleDateFormat.format(new Date())+(++i);
        }finally {
//            lock.unlock();
        }
    }
    public static void main1(String [] args) throws InterruptedException {
        Long start = System.currentTimeMillis();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++start");
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Long> result = new ArrayList<>();
        for (int i=0;i<100;i++){
            executorService.execute(()->{
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                lock.lock();
                Long res = Long.parseLong(getOrderCode());
//                lock.unlock();
                synchronized (result){
                    result.add(res);
                }
//                System.out.println("***add:"+res);
            });
        }
        latch.countDown();
        executorService.shutdown();
        while (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
        }
        System.out.println("###########################"+result.size());
//        result.forEach(System.out::println);

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++end");
        Map<Long, Long> map = result.stream().collect(Collectors.groupingBy(p -> p,Collectors.counting()));
        map.entrySet().stream().filter((e)->e.getValue()>1).forEach(System.out::println);
//        map.forEach((k, v) -> System.out.println(k + ":" + v));
        System.out.println("******************"+(System.currentTimeMillis()-start));
    }

    @Test
    public void testRedisOrderNub() throws InterruptedException {
        DistributedLock lock = new DistributedLock();
        lock.init();
        Long start = System.currentTimeMillis();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++start");
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Long> result = new ArrayList<>();

        for (int i=0;i<100;i++){
            executorService.execute(()->{
                long ex = 0L;
                try {
                    latch.await();
                    ex = lock.lock("111");
                    Long res = Long.parseLong(getOrderCode());
                    synchronized (result){
                        result.add(res);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {

                    lock.unlock("111",ex);
                }

//                System.out.println("***add:"+res);
            });
        }

        latch.countDown();
        executorService.shutdown();
        while (!executorService.awaitTermination(1, TimeUnit.SECONDS)) {
        }
        lock.cloePool();
        System.out.println("########################### result:"+result.size());
        result.sort((e1,e2)->e1.compareTo(e2));
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++end");
//        Map<Long, Long> map = result.stream().collect(Collectors.groupingBy(p -> p,Collectors.counting()));
//        map.entrySet().stream().filter((e)->e.getValue()>1).forEach(System.out::println);
//        map.forEach((k, v) -> System.out.println(k + ":" + v));
//        result.stream().filter();
        Optional<Long> max = result.stream().reduce(Long::max);
        System.out.println("max:"+max.get());
        System.out.println("max:"+result.get(result.size()-1));
        System.out.println("******************"+(System.currentTimeMillis()-start));
//                result.forEach(System.out::println);

    }


    @Test
    public void testZkOrderNub() throws InterruptedException {

        Long start = System.currentTimeMillis();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++start");
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Long> result = new ArrayList<>();
        for (int i=0;i<100;i++){
            executorService.execute(()->{
                Lock lock = new ZookeeperImproveLock();
                try {
                    latch.await();
                    lock.lock();
                    Long res = Long.parseLong(getOrderCode());
                    synchronized (result){
                        result.add(res);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }

//                System.out.println("***add:"+res);
            });
        }
        latch.countDown();
        executorService.shutdown();
        while (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
        }
        ZookeeperImproveLock.close();
        System.out.println("###########################"+result.size());
//        result.forEach(System.out::println);

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++end");
        Map<Long, Long> map = result.stream().collect(Collectors.groupingBy(p -> p,Collectors.counting()));
        map.entrySet().stream().filter((e)->e.getValue()>1).forEach(System.out::println);
//        map.forEach((k, v) -> System.out.println(k + ":" + v));
        System.out.println("******************"+(System.currentTimeMillis()-start));
        Optional<Long> max = result.stream().reduce(Long::max);
        System.out.println("max:"+max.get());
    }

    @Test
    public void testSnowFlake() throws InterruptedException {
        // 构造方法设置机器码：第2个机房的第5台机器
        Snowflake snowflake = new Snowflake(2, 5);
        System.out.println("********start");
        ExecutorService pool = Executors.newCachedThreadPool();
        List<Long> result = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(1);
        for (int i=0;i<1000;i++){
            pool.execute(()->{
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                long id1 = snowflake.nextId();
                synchronized (result){
                    result.add(id1);
                }
            });
        }
        latch.countDown();
        pool.shutdown();
        while (!pool.awaitTermination(1,TimeUnit.SECONDS)){

        }
        System.out.println("result:"+result.size());
        System.out.println("********end");
//        result.sort((e1,e2)->e1.compareTo(e2));

//        Optional<Long> max =result.stream().reduce(Long::max);
//        System.out.println("max:"+max.get());

        result.forEach(System.out::println);
        Map<Long, Long> map = result.stream().collect(Collectors.groupingBy(p -> p,Collectors.counting()));
        map.entrySet().stream().filter((e)->e.getValue()>1).forEach(System.out::println);

//        result.forEach((id)->System.out.println(String.format("%s => id: %d, hex: %s, bin: %s", snowflake.formatId(id), id,
//                BinHexUtil.hex(id), BinHexUtil.bin(id))));
    }






















    public static void main(String [] args) throws InterruptedException {
        DistributedLock lock = new DistributedLock();
        lock.setHost("127.0.0.1");
        lock.setPort(6379);
        lock.setLockTimeOut(10);
        lock.init();
        Long start = System.currentTimeMillis();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++start");
        List<Long> result = new ArrayList<>();

        for (int i=0;i<200;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long ex = 0L;
                    try {
                        ex = lock.lock("111");
                        while (ex<2){
                            System.out.println("while .... 111  ");
                            ex = lock.lock("111");
                            System.out.println("num:"+result.size());
                        }
                        System.out.println("num:"+result.size());
                        Long res = Long.parseLong(getOrderCode());
                        System.out.println("num **********************"+res);
//                    synchronized (result){
                        result.add(res);
//                    }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {

                        lock.unlock("111",ex);
                    }
                }
            }).start();
        }


//        lock.cloePool();
        System.out.println("########################### result:"+result.size());
//        result.sort((e1,e2)->e1.compareTo(e2));
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++end");
//        Map<Long, Long> map = result.stream().collect(Collectors.groupingBy(p -> p,Collectors.counting()));
//        map.entrySet().stream().filter((e)->e.getValue()>1).forEach(System.out::println);
//        map.forEach((k, v) -> System.out.println(k + ":" + v));
//        result.stream().filter();
//        Optional<Long> max = result.stream().reduce(Long::max);
//        System.out.println("max:"+max.get());
//        System.out.println("max:"+result.get(result.size()-1));
        System.out.println("******************"+(System.currentTimeMillis()-start));
    }
}
