package com.dong.base.test.thread;

import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/1/8.
 */
public class ExecutorTest {

    @Test
    public void test1(){
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);

        long initialDelay = 1;
        long period = 1;
        // 从现在开始1秒钟之后，每隔1秒钟执行一次job1
        service.scheduleAtFixedRate(new MyScheduledExecutor("job1"), initialDelay, period, TimeUnit.SECONDS);

        // 从现在开始2秒钟之后，每隔2秒钟执行一次job2
        service.scheduleWithFixedDelay(new MyScheduledExecutor("job2"), initialDelay, period, TimeUnit.SECONDS);
    }

}
