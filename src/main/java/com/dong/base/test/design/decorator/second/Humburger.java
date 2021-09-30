package com.dong.base.test.design.decorator.second;

/**
 * Created by Administrator on 2018/1/5.
 * 汉堡基类（被装饰者，相当于上面的Human）
 */
public abstract class Humburger {

    protected  String name ;

    public String getName(){
        return name;
    }

    public abstract double getPrice();

}
