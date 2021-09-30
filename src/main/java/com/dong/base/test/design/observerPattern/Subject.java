package com.dong.base.test.design.observerPattern;

import java.util.Vector;

/**
 * Created by Administrator on 2018/2/23.
 */
public abstract class Subject {

    private Vector<Observer> obs = new Vector();
    public void addObserver(Observer obs){
        this.obs.add(obs);
    }
    public void delObserver(Observer obs){
        this.obs.remove(obs);
    }
    protected void notifyObserver(){
        for(Observer o:obs){
            o.update();
        }
    }
    public abstract void doSomething();


}
