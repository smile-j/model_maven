package com.dong.base.lock.redis;

import com.dong.base.lock.OrderCodeGenerator;
import com.dong.base.lock.OrderUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.*;
import java.util.concurrent.*;

public class RedisLock {

    private Jedis jedis = null;
    public static JedisPool pool ;

    @Before
    public void init(){
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(10);
        pool = new JedisPool(poolConfig, "127.0.0.1", 6379);
        System.out.println("连接池初始化成功");

    }

    @After
    public void cloePool(){
        if(null != pool){
            pool.destroy();
            System.out.println("连接池关闭");
        }
    }

    /**
     *
     * closeJedis(释放redis资源)
     *
     * @Title: closeJedis
     * @param @param jedis
     * @return void
     * @throws
     */
    public static void closeJedis(Jedis jedis) {
        try {
            if (jedis != null) {
                /*jedisPool.returnBrokenResource(jedis);
                jedisPool.returnResource(jedis);
                jedisPool.returnResourceObject(jedis);*/
                //高版本jedis close 取代池回收
                jedis.close();
            }
        } catch (Exception e) {
            System.out.println("释放资源异常：" + e);
        }
    }


    @Test
    public void testGetLock(){
        DistributedLock distributedLock = new DistributedLock();
        distributedLock.setHost("127.0.0.1");
        distributedLock.setPort(6379);
        distributedLock.setLockTimeOut(10);
        for(int i=0;i<10;i++){
            long r = distributedLock.tryLock("111");
            System.out.println(i+" result："+r);
        }
    }

    @Test
    public void delLock(){
        unlock("111",10);
    }
    @Test
    public void testGetVal(){
        jedis = pool.getResource();
        String str = jedis.get("111");
        System.out.println("result:"+str);
    }
    @Test
    public void testOrder() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<String> result = new ArrayList<>();
        for (int i=0;i<100;i++){
            executorService.execute(()->{
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                result.add(OrderUtil.getOrderNum());
            });
        }
        latch.countDown();
        executorService.shutdown();
        while (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
        }
        System.out.println("********************size:"+result.size());
        result.sort((e1,e2)->{
            return Long.compare(Long.parseLong(e1),Long.parseLong(e2));

        });
        result.forEach(System.out::println);
    }



    /**
     * 得不到锁立即返回，得到锁返回设置的超时时间
     * @param key
     * @return
     */
    public long tryLock(String key,long lockTimeOut){
        //得到锁后设置的过期时间，未得到锁返回0
        long expireTime = 0;
        Jedis jedis = pool.getResource();
        expireTime = System.currentTimeMillis() + lockTimeOut +1;
        if (jedis.setnx(key, String.valueOf(expireTime)) == 1) {
            //得到了锁返回
            return expireTime;
        }else  {
            String curLockTimeStr =  jedis.get(key);
            //判断是否过期
            if (StringUtils.isBlank(curLockTimeStr)
                    || System.currentTimeMillis() > Long.valueOf(curLockTimeStr)) {
                expireTime = System.currentTimeMillis() + lockTimeOut +1;

                curLockTimeStr = jedis.getSet(key, String.valueOf(expireTime));
                //仍然过期,则得到锁
                if (StringUtils.isBlank(curLockTimeStr)
                        || System.currentTimeMillis() > Long.valueOf(curLockTimeStr)){
                    return expireTime;
                }else {
                    return 0;
                }
            }else {
                return 0;
            }
        }
    }

    /**
     * 得到锁返回设置的超时时间，得不到锁等待
     * @param key
     * @return
     * @throws InterruptedException
     */
    public long lock(String key,long lockTimeOut,long perSleep) throws InterruptedException{
        long starttime = System.currentTimeMillis();
        long sleep = (perSleep==0?lockTimeOut/10:perSleep);
        //得到锁后设置的过期时间，未得到锁返回0
        long expireTime = 0;
        Jedis jedis = pool.getResource();
        for (;;) {
            expireTime = System.currentTimeMillis() + lockTimeOut +1;
            if (jedis.setnx(key, String.valueOf(expireTime)) == 1) {
                //得到了锁返回
                return expireTime;
            }else  {
                String curLockTimeStr =  jedis.get(key);
                //判断是否过期
                if (StringUtils.isBlank(curLockTimeStr)
                        || System.currentTimeMillis() > Long.valueOf(curLockTimeStr)) {
                    expireTime = System.currentTimeMillis() + lockTimeOut +1;

                    curLockTimeStr = jedis.getSet(key, String.valueOf(expireTime));
                    //仍然过期,则得到锁
                    if (StringUtils.isBlank(curLockTimeStr)
                            || System.currentTimeMillis() > Long.valueOf(curLockTimeStr)){
                        return expireTime;
                    }else {
                        Thread.sleep(sleep);
                    }
                }else {
                    Thread.sleep(sleep);
                }
            }
            if (lockTimeOut > 0
                    && ((System.currentTimeMillis() - starttime) >= lockTimeOut)) {
                expireTime = 0;
                return expireTime;
            }
        }

    }

    /**
     * 先判断自己运行时间是否超过了锁设置时间，是则不用解锁
     * @param key
     * @param expireTime
     */
    public void unlock(String key,long expireTime){
        if (System.currentTimeMillis()-expireTime>0) {
            return ;
        }
        Jedis jedis = pool.getResource();
        String curLockTimeStr = jedis.get(key);
        System.out.println("haha:"+curLockTimeStr);
        if (StringUtils.isNotBlank(curLockTimeStr)&&Long.valueOf(curLockTimeStr)>System.currentTimeMillis()) {
            long jj = jedis.del(key);
            System.out.println("del "+jj);
        }
    }

    @Test
    public void testMap(){
        Map<String,Integer> map = new HashMap();
        map.put("aa",1);
        map.put("bb",1);
        map.put("cc",2);
        map.put("dd",1);
        map.put("ee",3);
        map.entrySet().stream().filter(e->e.getValue()>1).forEach(System.out::println);
    }

    @Test
    public void testRedisOrderNub() throws InterruptedException {
        Long start = System.currentTimeMillis();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++start");
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Long> result = new ArrayList<>();

        for (int i=0;i<10;i++){
            executorService.execute(()->{
                long ex = 0L;
                try {
                    latch.await();
                    ex = lock("111",5L,2L);
                    while (ex<1){
                        ex = lock("111",5L,2L);
                    }
                    if(ex>0){
                        Long res = Long.parseLong(OrderCodeGenerator.getOrderCode());
                        synchronized (result){
                            result.add(res);
                        }
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    unlock("111",ex);
                }

//                System.out.println("***add:"+res);
            });
        }
        latch.countDown();
        executorService.shutdown();
        while (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
        }
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

}
