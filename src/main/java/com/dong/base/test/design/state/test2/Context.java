package com.dong.base.test.design.state.test2;

//定义当前的状态
public class Context {

    private State state;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
    public String stateMessage(){
        return state.getState();
    }
}