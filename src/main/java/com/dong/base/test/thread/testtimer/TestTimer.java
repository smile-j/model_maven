package com.dong.base.test.thread.testtimer;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 * Created by Administrator on 2018/6/4.
 */
public class TestTimer {

//    @Test
//    public void test1(){
    public static void main(String [] args){
        testStart2();
    }

    //守护线程
    public static void testStart1(){
        System.out.println("当前时间："+new Date());
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.SECOND,10);
        Date date=calendar.getTime();
        MyTask task=new MyTask();
        Timer timer=new Timer(true);
        timer.schedule(task,date);
    }

    //非守护线程
    public static void testStart2(){
        System.out.println("当前时间："+new Date());
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.SECOND,10);
        Date date=calendar.getTime();
        MyTask task=new MyTask();
        Timer timer=new Timer();
        timer.schedule(task,date);
    }

}
