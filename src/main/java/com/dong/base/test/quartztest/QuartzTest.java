package com.dong.base.test.quartztest;


import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/5/24.
 *
 Scheduler，任务调度的API；它可以用来启动或者终止任务等。
 Job，具体的任务接口；通过实现它，来让任务调度执行具体的任务。
 JobDetail ，用来定义Job的实例。
 Trigger ，触发器用来定义给定的Job应该如何执行。
 JobBuilder ，用来定义/构建Jobdetail实例。
 TriggerBuilder ，用来定义/构建Trigger实例。

 */
//@Service
public class QuartzTest {

//    @Resource
//    private IBaseDAO baseDAO;

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

            //加入这个调度
            scheduler.scheduleJob(job, trigger);
            System.out.println(job.getKey() + " 运行在: " + runTime);
            //启动之
            scheduler.start();

            //运行一段时间后关闭
            Thread.sleep(10000);
            scheduler.shutdown(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void runQuar(){
    /*    try {
            List<Timer> timers = baseDAO.find("from ContentEntity ",null);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//2015-06-23 17:50:23
            System.out.println("------- 初始化 ----------------------");
            //创建scheduler 调度器
//            Date runTime = DateBuilder.evenSecondDateAfterNow();
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            System.out.println("------- 初始化完成 -----------");
            for (Timer timer:timers){
                JobDetail job = JobBuilder.newJob((Class<? extends Job>) Class.forName(timer.getAllPath())) //定义Job类为HelloQuartz类，这是真正的执行逻辑所在
                        .withIdentity("job" + timer.getId(), "group1") //定义name/group
//                        .usingJobData("name", "quartz") //定义属性
                        .build();

                if(timer.getType().equals("simple")){
                    // 一个scheduler 可以绑定多个job
                    // 一个 Job 在同一个 Scheduler 实例中通过名称和组名能唯一被标识
                    // 每5秒执行一次,共执行四次(注意参数为3)
                    SimpleScheduleBuilder builder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5)
                            .withRepeatCount(3);
                    Date date = simpleDateFormat.parse(timer.getJobCycel());
                    Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger_"+timer.getId(), "tGroup1").startAt(date)
                            .withSchedule(builder).build();
                    scheduler.scheduleJob(job, trigger);
                }else if(timer.getType().equals("cron")){
                    //CronTriggerImpl(String name, String group, String jobName, String jobGroup)
                    CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(timer.getJobCycel());
                    CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger_"+timer.getId(), "tGroup1")
                            .withSchedule(cronScheduleBuilder).build();
                    scheduler.scheduleJob(job, cronTrigger);
                }

            }
            scheduler.start();

        } catch (DAOException e) {
            e.printStackTrace();
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
    }
}
