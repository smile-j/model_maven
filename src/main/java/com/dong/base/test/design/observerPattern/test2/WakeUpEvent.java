package com.dong.base.test.design.observerPattern.test2;


/**
 * 定义事件类Event，Event事件类,将观察者状态的改变封装成Event类，不同的状态对应不同的事件。
 * 在我们的例子中，小孩的状态发生改变，他的观察者Dad、Dog、Grand会针对此事件做出反应
 */
public class WakeUpEvent {
    //描述了事件的一些基本的信息:时间+地点+被观察对象
    private long time;
    private String location;
    private Child child;

    public WakeUpEvent(long time, String location, Child child) {
        super();
        this.time = time;
        this.location = location;
        this.child = child;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

}
