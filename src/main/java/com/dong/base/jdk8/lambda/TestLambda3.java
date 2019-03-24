package com.dong.base.jdk8.lambda;

import com.dong.base.jdk8.model.Employee;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * java8 内置的四大核心函数式接口
 *
 * Consumer<T>:消费型接口
 *      void accept(T t);
 *
 *  Supplier<T>:供给型接口
 *      T get();
 *
 *  Function<T,R>:函数型接口
 *      R apply(T t);
 *
 *  Predicate<T>:判断型接口
 *      boolean test(T t);
 *
 */

public class TestLambda3 {

    //Predicate<T>断言型接口
    @Test
    public void test4(){
        List<String> list = Arrays.asList("Hello","Today","hh","ok","python");
        List<String> newList = filterStr(list,(str)-> str.length()>3);
        for(String str:newList){
            System.out.print("  "+str);
        }
    }
    //需求：将满足条件的字符串，放入集合中
    public List<String> filterStr(List<String > list,Predicate<String> pre){
        List<String> strList = new ArrayList<>();
        for(String str:list){
            if(pre.test(str)){
                strList.add(str);
            }
        }
        return strList;
    }

    //Function<T,R> 函数型接口
    @Test
    public void test3(){
        String newStr=strHandler("\t\t\t\n哈哈哈，不错一天！",(str)->str.trim());
        System.out.println(newStr);
        System.out.println(strHandler("哈哈哈，不错一天！",str->str.substring(4,9)));
    }

    //需求：用于处理字符串
    public String strHandler(String str,Function<String,String> fun){
        return fun.apply(str);
    }

    //Supplier<T> 供给型接口：
    @Test
    public void test2(){
        List<Integer> numList=getNumList(10,()->{
            return  (int)(Math.random()*100);
        });
        for(Integer integer:numList){
            System.out.println(integer);
        }

    }
    //需求：产生指定个数的整数，并放入集合中
    public List<Integer> getNumList(int num,Supplier<Integer> sup){
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<num;i++){
            list.add(sup.get());
        }
            return list;
    }

    //Consumer<T>消费者接口：
    @Test
    public void test1(){
        happy(10000,(money)->System.out.println("大保健："+money));
    }

    public void happy(double money,Consumer<Double> consumer){
        consumer.accept(money);
    }

}
