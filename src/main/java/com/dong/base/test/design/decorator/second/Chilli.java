package com.dong.base.test.design.decorator.second;

/**
 * Created by Administrator on 2018/1/5.
 * 辣椒（装饰者的第二层，相当于上面的decorator_first）
 */
public class Chilli extends Condiment {

    Humburger humburger;

    public Chilli(Humburger humburger) {
        this.humburger = humburger;

    }

    @Override
    public String getName() {
        return humburger.getName() + "-加辣椒 ";
    }

    @Override
    public double getPrice() {
        return humburger.getPrice()+0.5;  //辣椒是免费的哦
    }
}