package com.dong.base.test.thread;

/**
 * Created by Administrator on 2018/1/8.
 */
public  class MyScheduledExecutor implements Runnable {

    private String jobName;

    MyScheduledExecutor() {

    }

    MyScheduledExecutor(String jobName) {
        this.jobName = jobName;
    }

    @Override
    public void run() {
        System.out.println(jobName + " is running");
    }
}
