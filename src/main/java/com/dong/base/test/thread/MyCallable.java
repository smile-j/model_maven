package com.dong.base.test.thread;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * Created by Administrator on 2017/11/20.
 */
public class MyCallable implements Callable<Long> {
    @Override
    public Long call() throws Exception {
        Thread.sleep(2000);
        return new Date().getTime();
    }
}
