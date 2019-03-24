package com.dong.base.test.thread;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2018/1/8.
 */
public class TaskTest {
    /**
     * 线程池的管理工具
     * 调度型线程池
     */
    private final static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    /**
     * 官方案例
     */
    public static void beepForAnHour() {
        final Runnable beeper = new Runnable() {
            public void run() {
                System.out.println("beep");
            }
        };
        final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, 10, 100, TimeUnit.MILLISECONDS);
        scheduler.schedule(new Runnable() {
            public void run() {
                System.out.println("cancel");
                /**
                 * Attempts to cancel execution of this task. This attempt will fail if the task has already completed,
                 * has already been cancelled, or could not be cancelled for some other reason.
                 * If successful, and this task has not started when cancel is called, this task should never run. If the task has already started,
                 * then the mayInterruptIfRunning parameter determines whether the thread executing this task should be interrupted in an attempt to stop the task.
                 * After this method returns, subsequent calls to isDone() will always return true. Subsequent calls to isCancelled() will always return true if this method returned true.
                 * 试图取消执行这一任务。这种尝试会失败如果任务已经完成,已经被取消了,或者由于其他原因不能被取消。
                 * 如果成功的话,这个任务并没有开始时取消,这个任务不应该运行。如果任务已经开始,那么mayInterruptIfRunning参数决定是否应该中断线程执行这个任务,试图阻止这项任务。
                 * 该方法返回后,后续调用结束()将始终返回true。后续调用isCancelled()将始终返回true,如果这个方法返回true。
                 */
                beeperHandle.cancel(true);
                /**
                 *  如果这个任务被取消之前完成正常。
                 *  Returns true if this task was cancelled before it completed normally.
                 */
                System.out.println(beeperHandle.isCancelled());

            }
        }, 60*60, TimeUnit.MILLISECONDS);

    }

    /**
     *  Creates and executes a periodic action that becomes enabled first after the given initial delay,
     *  and subsequently with the given period; that is executions will commence after initialDelay then initialDelay+period,
     *  then initialDelay + 2 * period, and so on. If any execution of the task encounters an exception,
     *  subsequent executions are suppressed. Otherwise, the task will only terminate via cancellation or termination of the executor.
     *  If any execution of this task takes longer than its period, then subsequent executions may start late, but will not concurrently execute.
     *  创建并执行一个周期性的动作成为了第一个在给定的初始延迟之后,随后用给定的时期,这是执行后将开始initialDelay然后initialDelay +,然后initialDelay + 2 *时期,等等。
     *  如果任何执行任务遇到异常,后续执行的镇压。否则,只会终止的任务通过取消或终止执行器。如果执行这个任务花费的时间比其期,然后后续执行可能会迟到,但不会同时执行。
     *  command：执行线程
     *  initialDelay：初始化延时  启动以后多久执行
     *  period：两次开始执行最小间隔时间 距离上次执行的时间
     *  unit：计时单位
     */
    public static void scheduleAtFixedRate(){
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("scheduleAtFixedRate"+System.currentTimeMillis());
            }
        }, 1000, 100, TimeUnit.MILLISECONDS);
    }


    public static void main(String[] args) {
        try {
//          beepForAnHour();
//          scheduleAtFixedRate();
            scheduleWithFixedDelay();
//          lanuchTimer();
//          Thread.sleep(3000);
//          taskT();
//          Thread.sleep(1000 * 60);
//          studow();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Creates and executes a periodic action that becomes enabled first after the given initial delay,
     *  and subsequently with the given delay between the termination of one execution and the commencement of the next.
     *  If any execution of the task encounters an exception, subsequent executions are suppressed. Otherwise,
     *  the task will only terminate via cancellation or termination of the executor.
     *  创建并执行一个周期性的动作成为了第一个在给定的初始延迟之后,随后用给定的延迟之间的终止执行和接下来的毕业典礼。如果任何执行任务遇到异常,后续执行的镇压。
     *  否则,只会终止的任务通过取消或终止执行器。
     */
    public static void scheduleWithFixedDelay() {
        scheduler.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                System.out.println("scheduleWithFixedDelay---->"+System.currentTimeMillis());
            }
        }, 1000, 100, TimeUnit.MILLISECONDS);
    }


    /**
     * 关闭执行服务对象
     */
    public static void studow() {
        scheduler.shutdown();
    }

    /**
     * 凌晨3点钟执行
     */
    public static void taskTimr() {
        long oneDay = 24 * 60 * 60 * 1000;
        long initDelay  = getTimeMillis("20:00:00") - System.currentTimeMillis();
        initDelay = initDelay > 0 ? initDelay : oneDay + initDelay;

        scheduler.scheduleAtFixedRate(
                new EchoServer(),
                initDelay,
                oneDay,
                TimeUnit.MILLISECONDS);
    }
    /**
     * 获取指定时间对应的毫秒数
     * @param time "HH:mm:ss"
     * @return
     */
    private static long getTimeMillis(String time) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
            DateFormat dayFormat = new SimpleDateFormat("yy-MM-dd");
            Date curDate = dateFormat.parse(dayFormat.format(new Date()) + " " + time);
            return curDate.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    static int num=1;
    private static CountDownLatch cdl = new CountDownLatch(10);
    public String getCode(){
        String orderCode="";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd-hh-mm-ss") ;
      /*  lock.lock();
        try {
            orderCode  = format.format(new Date())+"-"+num++;
            System.out.println(Thread.currentThread()+"============="+orderCode);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }*/
        synchronized (orderCode){
            orderCode  = format.format(new Date())+"-"+num++;
            System.out.println(Thread.currentThread()+"============="+orderCode);
        }
//        countDownLatch.countDown();
        return orderCode;

    }

    //按照线程数初始化倒计数器
    private static CountDownLatch countDownLatch = new CountDownLatch(10);
    //java.util.concurrent
    private static Lock lock = new ReentrantLock();

    @Test
    public  void test1(){
        System.out.println("**********************************");
        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                   getCode();
                }
            }).start();
//            countDownLatch.getCount()
            countDownLatch.countDown();
        }
//        try {
//            countDownLatch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("+++++++++++++++++++++++++++++++++++++");

    }


    @Test
    public void test3() throws InterruptedException {
        ExecutorService pool =  Executors.newCachedThreadPool();
        System.out.println("00000000000000000000000000000000");
        for(int i=0;i<10;i++){
            final  int num = i;
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    int sum=0;
                    for(int j=0;j<500;j++){
                        try {
                            if(num>97)
                                Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        sum+=j;
                    }
                    System.out.println(Thread.currentThread().getName()+" :"+sum);
                }
            });
        }

        pool.shutdown();
        while (!pool.awaitTermination(1, TimeUnit.SECONDS)) {
            System.out.println("线程池没有关闭");
        }
        System.out.println("111111111111111111111111111111111111");
    }
}
