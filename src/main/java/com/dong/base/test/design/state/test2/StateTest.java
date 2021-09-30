package com.dong.base.test.design.state.test2;

public class StateTest {

    public static void main(String args[]){
        Context context=new Context();
        context.setState(new Rain());
        System.out.println(context.stateMessage());
        context.setState(new Sunshine());
        System.out.println(context.stateMessage());
    }

}
