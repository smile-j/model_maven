package com.dong.base.test.design.builder;

/**
 * 建造者模式
 *
 * 实现Builder接口，针对不同的商业逻辑，具体化复杂对象的各部分的创建。 在建造过程完成后，提供产品的实例
 *
 */
public class ManBuilder implements PersonBuilder {
    Person person;

    public ManBuilder() {
        person = new Person();//创建一个person实例，用于调用set方法
    }

    public void buildBody() {
        person.setBody("建造身体部分");
    }

    public void buildFoot() {
        person.setFoot("建造四肢部分");
    }

    public void buildHead() {
        person.setHead("建造头部部分");
    }

    public Person buildPerson() {
        return person;//返回一个person实例
    }
}
