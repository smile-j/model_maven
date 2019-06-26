package com.dong.base.test.design.mediatorPattern;

/**
 * Created by Administrator on 2018/2/23.
 */
public class ColleagueA extends AbstractColleague{
    public void setNumber(int number, AbstractMediator am) {
        this.number = number;
        am.AaffectB();
    }
}
class ColleagueB extends AbstractColleague{
    @Override
    public void setNumber(int number, AbstractMediator am) {
        this.number = number;
        am.BaffectA();
    }
}
