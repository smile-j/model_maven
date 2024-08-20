package com.dong.base.test.loader;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2018/5/24.
 */
public class TestClassLoader {

//    @Test
//    public void testDivClassLoader(){
    public static void main(String[] args){
        try {
            MyClassLoader mcl = new MyClassLoader();
            MyClassLoader mcl2 = new MyClassLoader();
            Class<?> clazz = null;
            clazz = Class.forName("UserInfo", true, mcl);
            Class clazz2 = Class.forName("UserInfo", true, mcl2);

            Object obj = clazz.newInstance();
            Object obj2 = clazz2.newInstance();
            System.out.println(obj);
            System.out.println(clazz.getClassLoader());//打印出我们的自定义类加载器
            System.out.println(clazz2.getClassLoader());//打印出我们的自定义类加载器


            clazz = null;
            System.gc();
            System.out.println(obj.getClass().getClassLoader().getParent());
            System.out.println(obj2.getClass().getClassLoader().getParent());
//            System.out.println(this.getClass().getClassLoader().getParent());//打印出我们的自定义类加载器
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void testRootBoot(){
//        System.out.println(Object.class.getClassLoader());// null
//        System.out.println(String.class.getClassLoader());// null
//        System.out.println(DNSNameService.class.getClassLoader());//sun.misc.Launcher$ExtClassLoader@7a9664a1
//        System.out.println(Driver.class.getClassLoader());// sun.misc.Launcher$AppClassLoader@43be2d65
    }


    @Test
    public void testFormatDate() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // 日期格式
        Date date = dateFormat.parse("2015-02-28"); // 指定日期
        Date newDate = addDate(date, 1); // 指定日期加上20天
        System.out.println(dateFormat.format(date));// 输出格式化后的日期
        System.out.println(dateFormat.format(newDate));
    }

    public static Date addDate(Date date,long day) throws ParseException {
        long time = date.getTime(); // 得到指定日期的毫秒数
        day = day*24*60*60*1000; // 要加上的天数转换成毫秒数
        time+=day; // 相加得到新的毫秒数
        return new Date(time); // 将毫秒数转换成日期
    }
}