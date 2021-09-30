package com.dong.base.lock.zk;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 *  create -s -e /zoe/ 22
 *
 *  [0000000005, 0000000006, 0000000007, 0000000008, zoe]
 *
 *
 * 多线程并发时，会存在竞态条件      对多个线程同时竞争的变量加锁，或者采用ReentrantLock底层的CAS算法
 *
 * Created by Administrator on 2018/1/29.
 */
public class ZookeeperImproveLock implements Lock {

    private static final String ZK_IP_PROT="localhost:2181";
    private static final String LOCK_PATH="/zkLock";

    private static ZkClient client = new ZkClient(ZK_IP_PROT,2000,2000,new SerializableSerializer());

    private static final Logger logger = LoggerFactory.getLogger(ZookeeperImproveLock.class);

    private  CountDownLatch countDownLatch = null;

    private String beforePath;// 当前请求的节点前一个节点
    private String currentPath;//当前请求的节点


    @Override
    //阻塞的方式获取锁
    public void lock() {
            if(!tryLock()){
                waitForLock();
                lock();
            }else {
                 System.out.println("==============get lock success========================");
            }


    }


    /**
     * tryLock()方法是有返回值的，它表示用来尝试获取锁，如果获取成功，则返回true，
     * 如果获取失败（即锁已被其他线程获取），则返回false，也就说这个方法无论如何都会立即返回。在拿不到锁时不会一直在那等待。
     * @return
     */
    @Override
    public boolean tryLock() {
        //若果currentPath为空则为第一次加锁，第一次加锁赋值currentPath
        if(currentPath==null||currentPath.length()<=0){
            //创建一个临时顺序节点
            currentPath = this.client.createEphemeralSequential(LOCK_PATH+"/","lock");
             System.out.println("---------------------------------" + currentPath);
        }

        //获取所有的临时节点排序，临时节点名称为自增的字符串如：000000000400
        List<String> childres = this.client.getChildren(LOCK_PATH);
        Collections.sort(childres);
        System.out.println(childres+"**********currentPath:"+currentPath+"******beforePath:"+beforePath+"*******"+childres.get(0));
        if (currentPath.equals(LOCK_PATH+"/"+childres.get(0))){//若果当前的节点在所有的节点中排名第一则获取锁成功
            return true;
        }else{//若果当前节点在所有的节点不是第一，则获取前面的节点名称，并赋值给beforePath
            int wz = Collections.binarySearch(childres,currentPath.substring(8));
            beforePath = LOCK_PATH+'/'+childres.get(wz-1);

        }

        return false;
    }

    /***
     * 该方法和tryLock()方法是类似的，只不过区别在于这个方法在拿不到锁时会等待一定的时间，在时间期限之内如果还拿不到锁，就返回false。
     * 如果如果一开始拿到锁或者在等待期间内拿到了锁，则返回true。
     * @param time
     * @param unit
     * @return
     * @throws InterruptedException
     */
    //通过新建节点的方式去尝试加锁  非阻塞
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

        System.out.println("########delete :"+currentPath);
        client.delete(currentPath);

    }

    private void waitForLock() {

        IZkDataListener listener = new IZkDataListener() {
            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                 System.out.println("===================catch data delete event===========================");
                if (countDownLatch != null){
                        countDownLatch.countDown();
                 }
            }

            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {

            }
        };

        client.subscribeDataChanges(beforePath,listener);
        if(client.exists(beforePath)){
           countDownLatch = new CountDownLatch(1);
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        client.unsubscribeDataChanges(beforePath,listener);// 取消监听beforePath节点

    }

    @Override
    public Condition newCondition() {
        return null;
    }

    /***
     * lockInterruptibly()方法比较特殊，当通过这个方法去获取锁时，如果线程正在等待获取锁，则这个线程能够响应中断，即中断线程的等待状态。
     * 也就使说，当两个线程同时通过lock.lockInterruptibly()想获取某个锁时，假若此时线程A获取到了锁，而线程B只有在等待，
     * 那么对线程B调用threadB.interrupt()方法能够中断线程B的等待过程。
     * @throws InterruptedException
     */
    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    public static void close(){
        client.close();
    }
}
