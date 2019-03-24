package com.dong.base.test.lock;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2018/2/2.
 */
public class TestMain {
    private static AtomicInteger count = new AtomicInteger(0);
    public static void main(String[] arg){
        count.addAndGet(2);
        System.out.println(count.get());
    }

}
