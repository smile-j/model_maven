package com.dong.base.test.design.strategyPattern;

/**
 * Created by Administrator on 2018/2/23.
 */
public class Context {

    private IStrategy iStrategy ;

    public Context(IStrategy iStrategy){
        this.iStrategy =iStrategy;

    }

    public void execute(){
        this.iStrategy.doSomething();
    }

}
