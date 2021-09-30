package com.dong.base.test.design.strategyPattern;

/**
 * Created by Administrator on 2018/2/23.
 */
public class ConcreteStrategy1 implements IStrategy {
    @Override
    public void doSomething() {
        System.out.println("具体策略1");
        }
    }
    class ConcreteStrategy2 implements IStrategy {
        public void doSomething() {
            System.out.println("具体策略2");
        }
    }