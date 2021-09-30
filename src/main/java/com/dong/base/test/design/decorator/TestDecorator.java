package com.dong.base.test.design.decorator;


import com.dong.base.test.design.decorator.first.*;
import com.dong.base.test.design.decorator.second.ChickenBurger;
import com.dong.base.test.design.decorator.second.Chilli;
import com.dong.base.test.design.decorator.second.Humburger;
import com.dong.base.test.design.decorator.second.Lettuce;
import org.junit.Test;

/**
 * Created by Administrator on 2018/1/5.
 * 装饰器模式
 * Decorator模式（别名Wrapper）：动态将职责附加到对象上，若要扩展功能，装饰者提供了比继承更具弹性的代替方案。
 * 动态地给一个对象添加一些额外的职责。就增加功能来说，Decorator模式相比生成子类更为灵活。
 *
 *
 *
 * 设计原则：

 1. 多用组合，少用继承。

 利用继承设计子类的行为，是在编译时静态决定的，而且所有的子类都会继承到相同的行为。然而，如果能够利用组合的做法扩展对象的行为，就可以在运行时动态地进行扩展。

 2. 类应设计的对扩展开放，对修改关闭。
 */
public class TestDecorator {

    @Test
    public void test1(){
        /**
         * 下面定义三种装饰(Decorator_zero,Decorator_first,Decorator_second)，这是第一个，第二个第三个功能依次细化，即装饰者的功能越来越多
         */
        Human person = new Person();
        Decorator decorator = new Decorator_second(new Decorator_first(
                new Decorator_zero(person)));
        decorator.wearClothes();
        System.out.println("************************");
        decorator.walkToWhere();
    }

    @Test
    public void test2(){
        Humburger humburger = new ChickenBurger();
        System.out.println(humburger.getName()+"  价钱："+humburger.getPrice());
        Lettuce lettuce = new Lettuce(humburger);
        System.out.println(lettuce.getName()+"  价钱："+lettuce.getPrice());
        Chilli chilli = new Chilli(humburger);
        System.out.println(chilli.getName()+"  价钱："+chilli.getPrice());
        Chilli chilli2 = new Chilli(lettuce);
        System.out.println(chilli2.getName()+"  价钱："+chilli2.getPrice());
    }

}
