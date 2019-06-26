package com.dong.base.test.design.observerPattern.jdk;

public class Main {

    public static void main(String[] args) {
        Publish publish = new Publish();
        Subscribe subscribe = new Subscribe(publish);

        publish.setData("开始");
    }

}
