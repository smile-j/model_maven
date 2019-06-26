package com.dong.base.test.design.mediatorPattern;

/**
 * Created by Administrator on 2018/2/23.
 */
public abstract  class AbstractMediator {

    protected AbstractColleague A;
    protected AbstractColleague B;
    public AbstractMediator(AbstractColleague a, AbstractColleague b) {
        A = a;
        B = b;
    }
    public abstract void AaffectB();
    public abstract void BaffectA();

}
