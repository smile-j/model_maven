package com.dong.base.test.design.state;

public class FullState implements DispenserState {

    @Override
    public void press() {
        System.out.println("Water is pouring!");
    }
}

 class NullState implements DispenserState {

    @Override
    public void press() {
        System.out.println("There is not water poured!");
    }
}
