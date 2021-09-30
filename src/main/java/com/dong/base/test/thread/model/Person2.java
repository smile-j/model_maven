package com.dong.base.test.thread.model;

import com.dong.base.model.UserEntity;

import java.util.Random;

/**
 * Created by Administrator on 2018/6/18.
 */
public class Person2 implements Cloneable{
    String name;
    int age;
    public static int num=0;
    UserEntity userEntity;


    public Person2 clone(){
        Person2 p = null;
        try {
            p = (Person2) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return p;
    }

    public Person2(){}
    public Person2(String name,int age){
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        if(userEntity!=null){
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", num=" + num +
                    ", userEntity=" + userEntity +
                    '}';
        }
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", num=" + num +
                '}';
    }



    public void testThread(){

        try {
            Random rd=new Random();
            Long   lg  = Long.valueOf(rd.nextInt(1000));
            System.out.println("name:"+Thread.currentThread().getName()+" lg:"+lg);
            Thread.sleep(lg);
            num++;
        System.out.println("name:"+Thread.currentThread().getName()+" num:"+num);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


