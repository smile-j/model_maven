package com.dong;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 令牌桶
 */
public class TokenBucket {

    private ArrayBlockingQueue<Integer> blockingQueue;
    private int limit;
    private int capacity;
    private TimeUnit unit;
    private int rate;
    private ScheduledExecutorService scheduledExecutorService= Executors.newScheduledThreadPool(1);

    public TokenBucket(int capacity,TimeUnit unit,int rate){
//存放令牌的队列
        this.blockingQueue=new ArrayBlockingQueue<>(capacity);
//令牌桶容量
        this.capacity=capacity;
//限流的时间单位
        this.unit=unit;
//限流的时间限制（速度），用于控制令牌生成速度
        this.rate=rate;
        init();
        start();
    }

    //初始化令牌桶
    private void init(){
        for(int i=0;i<capacity;i++){
            blockingQueue.add(i);
        }
    }

    //创建令牌
    private void createToken(){
        blockingQueue.offer(1);
    }

    //启动令牌创建定时线程池
    private void start(){
        scheduledExecutorService.scheduleAtFixedRate(this::createToken,rate,rate,unit);
    }

    //获取令牌资源
    public boolean acquire(){
        return blockingQueue.poll()==null?false:true;
    }

    //释放线程池资源
    public void release(){
        scheduledExecutorService.shutdown();
    }

    public static void main(String[] args) {
        TokenBucket tokenBucket5 = new TokenBucket(5,TimeUnit.MILLISECONDS,100);
        try{
            for(int i=0;i<100;i++){
                boolean acquire=tokenBucket5.acquire();
                System.out.printf("timestamp:%s,hello，result:%s\n",System.currentTimeMillis(),acquire);
                Thread.sleep(5L);
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally{
            tokenBucket5.release();
        }
    }

}
