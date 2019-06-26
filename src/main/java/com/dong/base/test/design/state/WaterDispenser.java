package com.dong.base.test.design.state;

public class WaterDispenser {
    private static int capacity = 20;
    private static DispenserState dispenserState;

    public WaterDispenser(DispenserState state) {
        dispenserState = state;
    }

    private static void setState(DispenserState state) {
        dispenserState = state;
    }

    public DispenserState getState() {
        return dispenserState;
    }

    public void press() {
        capacity--;
        if (capacity <= 0) {
            setState(new NullState());
        }
        dispenserState.press();
    }
}
