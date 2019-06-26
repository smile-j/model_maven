package com.dong.base.test.design.observerPattern;

import org.junit.Test;

/**
 * Created by Administrator on 2018/2/23.
 */
public class TestObserver {

    @Test
    public void test1(){

        Subject sub = new ConcreteSubject();
        sub.addObserver(new ConcreteObserver1()); //添加观察者1
        sub.addObserver(new ConcreteObserver2()); //添加观察者2
        sub.doSomething();

    }

}
