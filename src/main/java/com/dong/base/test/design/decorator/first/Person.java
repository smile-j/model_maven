package com.dong.base.test.design.decorator.first;

/**
 * Created by Administrator on 2018/1/5.
 * 定义被装饰者，被装饰者初始状态有些自己的装饰
 */
public class Person implements Human {


    private Integer age;
    private String name;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    public Person(){

    }

    @Override
    public void wearClothes() {
        // TODO Auto-generated method stub
        System.out.println("穿什么呢。。");
    }

    @Override
    public void walkToWhere() {
        // TODO Auto-generated method stub
        System.out.println("去哪里呢。。");
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}