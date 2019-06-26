package com.dong.base.jdk8.imp;

import com.dong.base.jdk8.TestInterface;
import com.dong.base.jdk8.TestInterface2;

/**
 * Created by Administrator on 2017/11/12.
 */
public class TestInterfaceImpl implements TestInterface,TestInterface2 {
    @Override
    public double calculate(int a) {
        return a;
    }


    @Override
    public void defaultMethod(){

    }

    @Override
    public double sqrt(int a) {
        return 0;
    }

}
