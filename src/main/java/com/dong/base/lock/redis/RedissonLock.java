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
            isLock = disLock.tryLock(500, 15000, TimeUnit.MILLISECONDS);
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
