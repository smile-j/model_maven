package com.dong.base.test.thread.morethread;

import java.util.concurrent.*;

/**
 * @author dongjunpeng
 * @Description  可以通过方法抛出异常，调用放可以获取异常信息
 * @date 2021/9/13
 */
public class TestCallable {

    public static final String OK ="OK";

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        MyCallable callable = new MyCallable();
        FutureTask<String> future = new FutureTask(callable);
        Thread t = new Thread(future);
        try {
            t.start();
//            System.out.println(future.get());
        }catch (Exception e){
            System.out.println("exception:"+e.getMessage());
        }
        t.join();
        System.out.println("..........end");
    }

    public static class MyCallable implements Callable<String>{

        @Override
        public String call() throws Exception {
            System.out.println("..mycallable....start");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            int aa =2;
//            if(aa==2){
//                throw new RuntimeException("11");
//            }
            System.out.println("..mycallable....");
            int s = 1/0;
            System.out.println("..mycallable....end");
            return OK;
        }
    }
}
