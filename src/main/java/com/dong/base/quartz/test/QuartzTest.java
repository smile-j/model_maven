package com.dong.base.quartz.test;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * Created by Administrator on 2018/5/24.
 */
public class QuartzTest {

    public static void main(String[] args) {
        try {

            System.out.println("------- 初始化 ----------------------");
            //创建scheduler 调度器
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            System.out.println("------- 初始化完成 -----------");

            //任务执行时间
            //Date runTime = DateBuilder.evenMinuteDate(new Date());
            Date runTime = DateBuilder.evenSecondDateAfterNow();

            //定义一个Trigger 触发器
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger1", "group1") //定义name/group
//                    .startNow()//一旦加入scheduler，立即生效
                    .startAt(runTime)//设置触发开始的时间
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule() //使用SimpleTrigger
                            .withIntervalInSeconds(1) //时间间隔   每隔一秒执行一次
                            .repeatForever()) //重复次数  一直执行，奔腾到老不停歇   .withRepeatCount(5)
                    .build();//产生触发器

            //定义一个JobDetail
            JobDetail job = JobBuilder.newJob(HelloQuartz.class) //定义Job类为HelloQuartz类，这是真正的执行逻辑所在
                    .withIdentity("job1", "group1") //定义name/group
                    .usingJobData("name", "quartz") //定义属性
                    .build();

            JobDetail job2 = JobBuilder.newJob(HelloQuartz.class) //定义Job类为HelloQuartz类，这是真正的执行逻辑所在
                    .withIdentity("job2", "group1") //定义name/group
                    .usingJobData("name", "quartz") //定义属性
                    .build();
            Trigger trigger2 = TriggerBuilder.newTrigger()
                    .withIdentity("trigger2", "group1") //定义name/group
//                    .startNow()//一旦加入scheduler，立即生效
                    .startAt(runTime)//设置触发开始的时间
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()) //使用SimpleTrigger
//                            .withIntervalInSeconds(1) //时间间隔   每隔一秒执行一次
//                            .repeatForever()) //重复次数  一直执行，奔腾到老不停歇   .withRepeatCount(5)
                    .build();//产生触发器
            //加入这个调度
            scheduler.scheduleJob(job, trigger);
            scheduler.scheduleJob(job2, trigger2);
            System.out.println(job.getKey() + " 运行在: " + runTime);
            //启动之
            scheduler.start();

            //运行一段时间后关闭
            Thread.sleep(10000);
            //暂停
            scheduler.pauseJob(job.getKey());
            System.out.println("-------------------------------------------");
            //恢复Job任务开始执行
            Thread.sleep(5000);
            scheduler.resumeJob(job.getKey());
            System.out.println("-------------------------------------------2222");
            Thread.sleep(5000);
            scheduler.deleteJob(job.getKey());
            System.out.println("-------------------------------------------杀死");
            Thread.sleep(5000);
            scheduler.scheduleJob(job,trigger);
            Thread.sleep(10000);
            scheduler.shutdown(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void runQuar(){

    }
}
