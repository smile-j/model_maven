package com.dong.base.test.design.observerPattern;

/**
 * Created by Administrator on 2018/2/23.
 */
public class ConcreteSubject extends Subject {
    @Override
    public void doSomething() {
        System.out.println("被观察者事件发生");
        this.notifyObserver();
    }
}
