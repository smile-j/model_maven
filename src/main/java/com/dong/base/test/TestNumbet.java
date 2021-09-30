package com.dong.base.test;

import org.junit.Test;

import java.util.*;

/**
 * Created by dong on 2017/1/10.
 */
public class TestNumbet  {
    @Test
    public void testOne(){
        String str1="123";
        String str2="q123";
        String str3="aaa";
        String str4="12.345";
//        System.out.println(MathisNaN()+"-----"++"-------------------");
        List<Map> list = new ArrayList<Map>();
        for(int i=0;i<3;i++){
            Map map = new HashMap();
            map.put(1,2);
            map.put(2,3);
            map.put(3,4);
            list.add(map);
        }
        System.out.println(list.size());
    }

    @Test
    public void testStr(){
        String aa=  new String("1");
        System.out.println("-----"+aa);
        addStr(aa);
        System.out.println("-----"+aa);
    }

    public void addStr(String a){
        a=a+" he he ";
    };
    String separator = ",";
    @Test
//    public void testAddStr(String ... a){
    public void testAddStr(){
//        System.out.println("哈哈"+a.length);
        List list = Arrays.asList("aa", "bb", "cc");
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next()+separator);
        }
    };


    @Test
    public  void haha(){
        System.out.println("afa");
    }
}
