package com.dong.base.test.thread;

/**
 * Created by Administrator on 2020/12/1.
 */
public class StopThread {
    private static StopThread ourInstance = new StopThread();

    public static StopThread getInstance() {
        return ourInstance;
    }

    private StopThread() {
    }
}
