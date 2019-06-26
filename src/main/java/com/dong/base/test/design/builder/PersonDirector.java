package com.dong.base.test.design.builder;

//调用具体建造者来创建复杂对象的各个部分，在指导者中不涉及具体产品的信息，只负责保证对象各部分完整创建或按某种顺序创建

public class PersonDirector {
    public Person constructPerson(PersonBuilder pb) {
        //按照 身体--->头部--->四肢 的顺序创建人物
        pb.buildHead();
        pb.buildBody();
        pb.buildFoot();
        return pb.buildPerson();
    }
}
