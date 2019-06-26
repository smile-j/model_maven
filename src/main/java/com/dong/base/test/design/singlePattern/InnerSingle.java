package com.dong.base.test.design.singlePattern;

public class InnerSingle {

    private static class Singletion{
        private static Singletion single = new Singletion();
    }

    public static Singletion getInstance(){
        return Singletion.single;
    }
}
