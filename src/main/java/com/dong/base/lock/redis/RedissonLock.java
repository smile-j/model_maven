package com.dong.base.lock.redis;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

public class RedissonLock {

    public static void main(String[] args) {

// 构造redisson实现分布式锁必要的Config
        Config config = new Config();
//        config.useSingleServer().setAddress("redis://172.29.1.180:5379").setPassword("a123456").setDatabase(0);
        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setDatabase(0);
// 构造RedissonClient
        RedissonClient redissonClient = Redisson.create(config);
// 设置锁定资源名称
        RLock disLock = redissonClient.getLock("DISLOCK");
        boolean isLock;
        try {
            //尝试获取分布式锁
            //tryTime 秒之后停止重试加锁，返回false
            //具有 Watch Dog 自动延期机制，默认续30s 每隔30/3=10 秒续到30
            long tryTime = 500;
            isLock = disLock.tryLock();
            isLock = disLock.tryLock(tryTime, TimeUnit.MILLISECONDS);
            isLock = disLock.tryLock(tryTime, 15000, TimeUnit.MILLISECONDS);
            //只有 leaseTime(默认-1) 等于 -1 时，才具有 Watch Dog 自动延期机制，默认续30s 每隔30/3=10 秒续到30s
            boolean locked3 = disLock.tryLock(tryTime, -1,  TimeUnit.MILLISECONDS);
            if (isLock) {
                System.out.println("获取lock 开始执行业务代码");
                //TODO if get lock success, do something;
                Thread.sleep(15000);
                System.out.println("业务代码执行完毕.......");
            }
            System.out.println("释放锁。。。。。end");
        } catch (Exception e) {
        } finally {
            // 无论如何, 最后都要解锁
            disLock.unlock();
        }


    }

}
