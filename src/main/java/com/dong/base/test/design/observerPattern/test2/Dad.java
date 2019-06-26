package com.dong.base.test.design.observerPattern.test2;

/*
 * 具体观察者ConcreteObserver，被观察的对象child的状态发生变化这一事件会触发观察者的performAction方法，针对被观察者的变化做出反应
 */
//具体观察者一
class Dad implements WakeUpListener {

    public void performAction(WakeUpEvent wakeUpEvent) {
        System.out.println("..feed..");
    }

}
//具体观察者二
class Dog implements WakeUpListener {

    public void performAction(WakeUpEvent wakeUpEvent) {
        System.out.println("..汪汪..");
    }

}
//具体观察者三
class Grand implements WakeUpListener {

    public void performAction(WakeUpEvent wakeUpEvent) {
        System.out.println("..hug..");
    }

}
