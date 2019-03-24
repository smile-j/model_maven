package com.dong.base.test.design.observerPattern;

/**
 * Created by Administrator on 2018/2/23.
 */
public class ConcreteObserver1 implements Observer {
    @Override
    public void update() {
        System.out.println("观察者1收到信息，并进行处理。");
    }
}
class ConcreteObserver2 implements Observer {
    public void update() {
        System.out.println("观察者2收到信息，并进行处理。");
    }
}