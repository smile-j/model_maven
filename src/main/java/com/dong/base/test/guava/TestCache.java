package com.dong.base.test.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.junit.Test;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;


/**
 * 1.maximumSize 设置构建缓存容器的最大容量，当缓存数量达到该容量时，会删除其中的缓存
 * 2.concurrencyLevel 设置并发等级，也就是同时操作缓存的线程数。默认是4
 * 3. expireAfterWrite 缓存写入多久后过期
 * 4. refreshAfterWrite 缓存写入多久后刷新
 * 5. initialCapacity：设置内部哈希表的最小容量,这个值的设置通常与concurrencyLevel方法相关
 * 6.
 *
 *
 *
 */
public class TestCache {


    //设置缓存时间,且在指定时间没有被访问过
    @Test
    public void testCacheTime() throws InterruptedException {
        Cache<String, String> cache = CacheBuilder.newBuilder().expireAfterAccess(10, TimeUnit.SECONDS).build();
        for (int i=1;i<16;i++){
            cache.put(MessageFormat.format("cache key:{0}",i),MessageFormat.format("cache value:{0}",i));
            TimeUnit.SECONDS.sleep(1);
            if(i==9){
                cache.getIfPresent("cache key:1");
            }
        }
        System.out.println("输出..........");
        for (int i=1;i<31;i++){
            System.out.println(cache.getIfPresent(MessageFormat.format("cache key:{0}",i)));
        }
    }

    //测试最大放入长度
    @Test
    public  void testMaxSize() {
        Cache<Object, Object> cache = CacheBuilder.newBuilder().maximumSize(8L).build();
        for (int i=0;i<11;i++){
            cache.put(MessageFormat.format("cache key:{0}",i),MessageFormat.format("cache value:{0}",i));
        }
        System.out.println(cache.getIfPresent("cache key:1"));
        System.out.println(cache.getIfPresent("cache key:10"));
        System.out.println(cache.getIfPresent("cache key:11"));
    }

}
