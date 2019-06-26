package com.dong.base.test.thread;

/**
 * Created by Administrator on 2018/1/8.
 */
public class EchoServer implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("This is a echo server. The current time is " +
                System.currentTimeMillis() + ".");
    }
}

