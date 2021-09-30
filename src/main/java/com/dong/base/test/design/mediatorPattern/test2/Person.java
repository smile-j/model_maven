package com.dong.base.test.design.mediatorPattern.test2;

public abstract class Person {
    protected String name;
    protected Mediator mediator;

    Person(String name,Mediator mediator){
        this.name = name;
        this.mediator = mediator;
    }

}
