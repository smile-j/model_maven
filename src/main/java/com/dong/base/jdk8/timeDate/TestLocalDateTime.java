package com.dong.base.jdk8.timeDate;

import org.apache.commons.lang3.BooleanUtils;
import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Set;

/**
 * Created by Administrator on 2017/11/19.
 */
public class TestLocalDateTime {

    /**
     * 字符串转LocalDateTime
     */
    @Test
    public void test9(){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        //格式化:日期-->字符串
        LocalDateTime localDateTime = LocalDateTime.now();
        String str1 = formatter.format(localDateTime);
        System.out.println(localDateTime);
        System.out.println(str1);//2019-02-18T15:42:18.797

        //解析：字符串 -->日期
        TemporalAccessor parse = formatter.parse("2019-02-18T15:42:18.797");
        System.out.println(parse);
    }

    //ZoneDate ZoneDTime ZoneDateTime
    @Test
    public void test8(){
       LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Europe/Copenhagen"));
        System.out.println(ldt);

        LocalDateTime ldt2 = LocalDateTime.now();
        ZonedDateTime zdt = ldt2.atZone(ZoneId.of("Europe/Copenhagen"));
        System.out.println(zdt);

    }

    @Test
    public void test7(){
       Set<String> set =ZoneId.getAvailableZoneIds();
        set.forEach(System.out::println);
    }

    //DateTimeFormatter:格式化时间/日期
    @Test
    public void test6(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
//        DateTimeFormatter.ISO_DATE;DateTimeFormatter.ISO_DATE_TIME
        LocalDateTime ldt = LocalDateTime.now();

        String strDate = ldt.format(dateTimeFormatter);
        System.out.println(strDate);
        System.out.println("-----------------------------------------------");

        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String strDate2 = dtf2.format(ldt);
        System.out.println(strDate2);

        LocalDateTime newDate = ldt.parse(strDate2,dtf2);
        System.out.println(newDate);

    }

    //TemporalAdjuster:时间校正器
    @Test
    public void test5(){
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ldt2 = ldt.withDayOfMonth(11);
        System.out.println(ldt2);

        LocalDateTime ldt3 = ldt2.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        System.out.println(ldt3);

        //自定义：下一个工作日
        LocalDateTime ldt5= ldt.with((l) -> {
            LocalDateTime ldt4 = (LocalDateTime) l;

            DayOfWeek dow = ldt4.getDayOfWeek();
            if (dow.equals(DayOfWeek.FRIDAY)) {
                return ldt4.plusDays(3);
            } else if (dow.equals(DayOfWeek.SATURDAY)) {
                return ldt4.plusDays(2);
            } else {
                return ldt4.plusDays(1);
            }
        });

        System.out.println(ldt5);


    }

    //3.
    //Duration :计算俩个时间的间隔
    //Period :计算俩个日期之间的间隔
    @Test
    public void test4(){
        //Period
        LocalDate ld1 = LocalDate.of(2016,11,11);
        LocalDate ld2 = LocalDate.now();

        Period period = Period.between(ld1,ld2);
        System.out.println(period);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
    }
    @Test
    public void test3(){
        //Duration
        Instant instant1 = Instant.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant instant2 = Instant.now();

        Duration duration =Duration.between(instant1, instant2);
        System.out.println(duration.toMillis());
        System.out.println("---------------------------------------------------");

        LocalTime lt1 = LocalTime.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LocalTime lt2 = LocalTime.now();
        System.out.println(Duration.between(lt1,lt2).toMillis());

    }

    //2.Instant:时间戳(以Uinx元年：1970年1月1日 00:00:00到某个时间之间的毫秒值)
    @Test
    public void test2(){
        Instant instant1 = Instant.now();//默认获取UTC时区
        System.out.println(instant1);

       OffsetDateTime offset = instant1.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offset);

        System.out.println(instant1.toEpochMilli());

        Instant instant2 = Instant.ofEpochSecond(60);
        System.out.println(instant2);

        Date date = new Date();
        Instant instant = date.toInstant();
        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
//        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    }

    //1. LocalDate LocalTime LocalDateTime
    @Test
    public void test1(){
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);
        DayOfWeek dayOfWeek = ldt.getDayOfWeek();
        boolean flag = dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;

        LocalDateTime ldt2 = LocalDateTime.of(2015, 10, 19, 22, 33);
        System.out.println(ldt2);

        System.out.println(ldt.plusYears(2));
        System.out.println(ldt.minusHours(2));
        System.out.println(ldt.getYear());
        System.out.println(ldt.getMonthValue());
        System.out.println("日："+ldt.getDayOfMonth());
        System.out.println("日："+ldt.getDayOfYear());
        System.out.println("日："+ldt.getDayOfWeek());
        System.out.println(ldt.getHour());
        System.out.println(ldt.getMinute());
        System.out.println(ldt.getSecond());
    }

//    @Test
//    public void test3(){
//        LocalDate localDate = LocalDate.now();
//        System.out.println(localDate);
//    }
}
