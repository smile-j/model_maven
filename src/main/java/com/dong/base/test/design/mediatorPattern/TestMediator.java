package com.dong.base.test.design.mediatorPattern;

import org.junit.Test;

/**
 * Created by Administrator on 2018/2/23.
 *
 * 中介者模式
 */
public class TestMediator {

    @Test
    public void test1(){

        AbstractColleague collA = new ColleagueA();
        AbstractColleague collB = new ColleagueB();
        AbstractMediator am = new Mediator(collA, collB);
        System.out.println("==========通过设置A影响B==========");
        collA.setNumber(1000, am);
        System.out.println("collA的number值为："+collA.getNumber());
        System.out.println("collB的number值为A的10倍："+collB.getNumber());
        System.out.println("==========通过设置B影响A==========");
        collB.setNumber(1000, am);
        System.out.println("collB的number值为："+collB.getNumber());
        System.out.println("collA的number值为B的0.1倍："+collA.getNumber());

    }

}
