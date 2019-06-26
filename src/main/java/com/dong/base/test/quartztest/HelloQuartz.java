package com.dong.base.test.quartztest;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2018/5/24.
 */
public class HelloQuartz implements Job {

    //开发者实现该接口定义需要执行的任务。JobExecutionContext类提供调度上下文的各种信息
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //通过上下文获取
        JobKey jobKey = context.getJobDetail().getKey();

        DateFormat df = new SimpleDateFormat("yyyy年MM月dd日  HH时mm分ss秒");
        System.out.println(jobKey + "在" + df.format(new Date()) + "时，输出了：Hello World!!!");

        //do more这里可以执行其他需要执行的任务
    }
}
