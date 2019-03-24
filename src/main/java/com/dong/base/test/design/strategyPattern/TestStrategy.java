package com.dong.base.test.design.strategyPattern;

import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2018/2/23.
 * 策略模式
 */
public class TestStrategy {

    @Test
    public void test1(){
        Context context;
        System.out.println("-----执行策略1-----");
        context = new Context(new ConcreteStrategy1());
        context.execute();
        System.out.println("-----执行策略2-----");
        context = new Context(new ConcreteStrategy2());
        context.execute();
//        List
    }

}
