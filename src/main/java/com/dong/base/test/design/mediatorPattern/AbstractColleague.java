package com.dong.base.test.design.mediatorPattern;

/**
 * Created by Administrator on 2018/2/23.
 */
public abstract class AbstractColleague {

    protected int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

   abstract void setNumber(int number,AbstractMediator mediator);
//    abstract void aa();
}
