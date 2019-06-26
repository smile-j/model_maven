package com.dong.base.test.design.observerPattern.test2;

/**
 * 给出监听接口，具体的观察者都去实现这个接口，具体的观察者复写接口的performAction方法，小孩的状态发生变化，做出响应
 */
public interface WakeUpListener {
     public void performAction(WakeUpEvent wakeUpEvent);
 }