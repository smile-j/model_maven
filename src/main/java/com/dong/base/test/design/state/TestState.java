package com.dong.base.test.design.state;

public class TestState {

    public static void main(String[] args) {
        WaterDispenser dispenser = new WaterDispenser(new FullState());
        for (int i = 0; i < 100; ++i) {
            dispenser.press();
        }
    }

}
