package com.dong.base.test.thread.morethread;

import cn.hutool.core.thread.NamedThreadFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/9/29
 */
public class TestThreadPoolExecutor {

    public static void main(String[] args) {

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3, 3, 3L,
                TimeUnit.SECONDS,new LinkedBlockingQueue<>(11), new NamedThreadFactory("test",false),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

                    }
                }
        );
        // 提前初始化核心线程
        poolExecutor.prestartAllCoreThreads();

      /*  自定义拒绝策略
      poolExecutor.setRejectedExecutionHandler((r,executor)->{

        });*/

    }

}
