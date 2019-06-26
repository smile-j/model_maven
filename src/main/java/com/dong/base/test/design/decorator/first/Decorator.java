package com.dong.base.test.design.decorator.first;

/**
 * Created by Administrator on 2018/1/5.
 */
public abstract class Decorator implements Human {
//public abstract class Decorator extends Person {

    private Human human;

    public Decorator(Human human) {
        this.human = human;
    }

    public void wearClothes() {
        human.wearClothes();
    }

    public void walkToWhere() {
        human.walkToWhere();
    }

}
