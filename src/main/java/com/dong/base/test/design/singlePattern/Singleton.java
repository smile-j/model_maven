package com.dong.base.test.design.singlePattern;

//单利模式  懒汉 饿汉
public class Singleton {

    // 饿汉式
    private final static Singleton INSTANCE = new Singleton();

    private Singleton(){}

    public static Singleton getInstance(){
        return INSTANCE;
    }

    //懒汉

  /*  private static Singleton singleton;

    private Singleton() {}

    public static Singleton getInstance() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }*/

}
