package com.dong.base.jdk8.timeDate;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2017/11/18.
 */
public class TestDate {

    @Test
    public void test1(){
        SimpleDateFormat  sdf = new SimpleDateFormat("yyyyMMdd");

        Callable<Date> task = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
//                return sdf.parse("20171218");
                return DateFormatThreadLocal.convert("20171218");
            }
        };

        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future<Date>> results = new ArrayList<>();

        for(int i=0;i<10;i++ ){
            results.add(pool.submit(task));
        }

        for(Future<Date> future:results){
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        pool.shutdown();
    }

    @Test
    public void test2(){
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyyMMdd");

        Callable<LocalDate> task = new Callable<LocalDate>() {
            @Override
            public LocalDate call() throws Exception {
                return LocalDate.parse("20171218", sdf);
            }
        };

        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future<LocalDate>> results = new ArrayList<>();

        for(int i=0;i<10;i++ ){
            results.add(pool.submit(task));
        }

        for(Future<LocalDate> future:results){
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        pool.shutdown();


    }
}
