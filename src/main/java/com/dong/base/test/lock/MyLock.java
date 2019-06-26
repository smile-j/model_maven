package com.dong.base.test.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 多线程并发时，会存在竞态条件      对多个线程同时竞争的变量加锁，或者采用ReentrantLock底层的CAS算法
 *
 * Created by Administrator on 2018/1/29.
 */
public class MyLock implements Lock {
    @Override
    public void lock() {

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

    /**
     * tryLock()方法是有返回值的，它表示用来尝试获取锁，如果获取成功，则返回true，
     * 如果获取失败（即锁已被其他线程获取），则返回false，也就说这个方法无论如何都会立即返回。在拿不到锁时不会一直在那等待。
     * @return
     */
    @Override
    public boolean tryLock() {
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
