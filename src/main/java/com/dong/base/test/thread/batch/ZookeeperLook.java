package com.dong.base.test.thread.batch;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by Administrator on 2018/1/11.
 */
public class ZookeeperLook implements Lock{
    private static final String ZK_IP_PROT="localhost:2181";
    private static final String LOCK_NODE="/LOCK";

//    private ZkClient zkClient;

    @Override
    public void lock() {

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
