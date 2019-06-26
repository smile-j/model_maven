package com.dong.base.test.design.observerPattern.test2;

import java.util.ArrayList;
import java.util.List;

public class Child implements Runnable {
    //用List来存放不同的监听
    private List<WakeUpListener> wakeUpListeners = new ArrayList<WakeUpListener>();

    //List中添加监听的操作
    public void addWakenUpListener(WakeUpListener l) {
        wakeUpListeners.add(l);
    }

    //被观察者小孩的状态发生改变，则会通知观察者，使其执行各自的performAction方法
    public void wakeUp() {
        for (int i = 0; i < wakeUpListeners.size(); i++) {
            WakeUpListener l = wakeUpListeners.get(i);
            //
            l.performAction(new WakeUpEvent(System.currentTimeMillis(), "沙发上",
                    this));
        }
    }

    //监听线程的run()
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.wakeUp();
    }

}