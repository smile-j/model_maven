package com.dong.base.test.thread.testtimer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

/**
 * Created by Administrator on 2018/6/4.
 */
public class MyTask extends TimerTask {

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss SS");

    @Override
    public void run() {
        System.out.println("任务执行了，时间为："+format.format(new Date()));
    }
}
