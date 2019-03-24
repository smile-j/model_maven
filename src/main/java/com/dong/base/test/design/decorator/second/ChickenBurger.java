package com.dong.base.test.design.decorator.second;

/**
 * Created by Administrator on 2018/1/5.
 * 鸡腿堡类（被装饰者的初始状态，有些自己的简单装饰，相当于上面的Person）
 */
public class ChickenBurger extends Humburger {

    public ChickenBurger(){
        name = "鸡腿堡";
    }

    @Override
    public double getPrice() {
        return 10;
    }
}
