package com.dong.base.lock.redis;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.After;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


/**
 * redis实现的distributedlock ,锁占用时间不宜过长
 *
 * @author wyzssw
 */
public class DistributedLock {
    private String host;

    private Integer port;

    //单位毫秒 锁超时时间，防止线程在入锁以后，无限的执行等待
    private long lockTimeOut =5 * 1000;
    /**
     * 锁等待时间，防止线程饥饿
     */
//    private int timeoutMsecs = 1 * 1000;
    private int timeoutMsecs = 10*1000;

    private static final int DEFAULT_ACQUIRY_RESOLUTION_MILLIS = 100;

    private volatile boolean locked = false;

    private Jedis jedis ;
    public static JedisPool pool ;




    /**
     * @return the lockTimeOut
     */
    public long getLockTimeOut() {
        return lockTimeOut;
    }


    /**
     * 得不到锁立即返回，得到锁返回设置的超时时间
     * @param key
     * @return
     */
    public long tryLock(String key){
        //得到锁后设置的过期时间，未得到锁返回0
        long expireTime = 0;
        Jedis jedis = null;
        jedis = new Jedis(host, port);
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
    public  long lock(String key) throws InterruptedException{
        //得到锁后设置的过期时间，未得到锁返回0
        long expireTime = 0;
        Jedis jedis = pool.getResource();

        int timeout = timeoutMsecs;
//        while (timeout>=0) {
        while (true) {
            expireTime = System.currentTimeMillis() + lockTimeOut +1;
            if (jedis.setnx(key, String.valueOf(expireTime)) == 1) {
                //得到了锁返回
//                jedis.expire(key,expireTime);设置过期时间
                System.out.println(Thread.currentThread().getName()+"......................get lock");
                locked = true;
                break;
            }else  {
                String curLockTimeStr =  jedis.get(key);
                //判断是否过期
                if (StringUtils.isBlank(curLockTimeStr)
                        || System.currentTimeMillis() > Long.valueOf(curLockTimeStr)) {
                    expireTime = System.currentTimeMillis() + lockTimeOut +1;

                    String oldValueStr = jedis.getSet(key, String.valueOf(expireTime));
                    //仍然过期,则得到锁
                    System.out.println(oldValueStr+"-------------------"+curLockTimeStr);
                    if (StringUtils.isBlank(oldValueStr) ||oldValueStr.equals(curLockTimeStr)){
                        System.out.println(Thread.currentThread().getName()+"......................get lock");
                        locked = true;
                       break;
                    }
                }
            }
            timeout -= DEFAULT_ACQUIRY_RESOLUTION_MILLIS;
            Thread.sleep(DEFAULT_ACQUIRY_RESOLUTION_MILLIS);
        }
        jedis.close();
        return expireTime;

    }

    /**
     * 先判断自己运行时间是否超过了锁设置时间，是则不用解锁
     * @param key
     */
    public  void unlock(String key,long expireTime){
       /* if (System.currentTimeMillis()-expireTime>0) {
            return ;
        }
        Jedis jedis = pool.getResource();
        String curLockTimeStr = jedis.get(key);
        if (StringUtils.isNotBlank(curLockTimeStr)&&Long.valueOf(curLockTimeStr)>System.currentTimeMillis()) {
            jedis.del(key);
        jedis.close();
        }*/
        if (locked) {
            Jedis jedis = pool.getResource();
            jedis.del(key);
            jedis.close();
            System.out.println(Thread.currentThread().getName()+"..............del lock");
            locked = false;
        }

        /**
         * 释放分布式锁
         * @param jedis Redis客户端
         * @param lockKey 锁
         * @param requestId 请求标识
         * @return 是否释放成功
         */
       /* public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {

            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));

            if (RELEASE_SUCCESS.equals(result)) {
                return true;
            }
            return false;

        }*/
    }


    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @param port the port to set
     */
    public void setPort(Integer port) {
        this.port = port;
    }


    /**
     * @param lockTimeOut the lockTimeOut to set
     */
    public void setLockTimeOut(long lockTimeOut) {
        this.lockTimeOut = lockTimeOut;
    }

    public int getTimeoutMsecs() {
        return timeoutMsecs;
    }

    public void setTimeoutMsecs(int timeoutMsecs) {
        this.timeoutMsecs = timeoutMsecs;
    }

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


    public  long lock2(String key) throws InterruptedException{
        //得到锁后设置的过期时间，未得到锁返回0
        long expireTime = 0;
        Jedis jedis = pool.getResource();
        expireTime = System.currentTimeMillis() + lockTimeOut +1;
        Long result  = jedis.setnx(key, String.valueOf(expireTime)) ;
        jedis.close();
        if (result == 1) {
            //得到了锁返回
            System.out.println(Thread.currentThread().getName()+"..............get  lock");
            return expireTime;
        }else {
            return 0;
        }
    }

    public void unlock2(String key,long expireTime){
        Jedis jedis = pool.getResource();
//        System.out.println(jedis.get(key)+"__________________________"+expireTime);
        if(jedis.get(key).equals(String.valueOf(expireTime))){
            jedis.del(key);
            jedis.close();
            System.out.println(Thread.currentThread().getName()+"..............del  lock");
        }

        }

        /**
         * 释放分布式锁
         * @param jedis Redis客户端
         * @param lockKey 锁
         * @param requestId 请求标识
         * @return 是否释放成功
         */
       /* public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {

            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));

            if (RELEASE_SUCCESS.equals(result)) {
                return true;
            }
            return false;

        }*/

}
