package com.dong.base.test.design.decorator.second;

/**
 * Created by Administrator on 2018/1/5.
 * 生菜（装饰者的第一层，相当于上面的decorator_zero）
 */
public class Lettuce extends Condiment {

    Humburger humburger;

    public Lettuce(Humburger humburger){
        this.humburger = humburger;
    }

    @Override
    public String getName() {
        return humburger.getName()+"-加生菜 ";
    }

    @Override
    public double getPrice() {
        return humburger.getPrice()+1.5;
    }
}
