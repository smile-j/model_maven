package com.dong.base.test.design.observerPattern.jdk;

import java.util.Observable;
import java.util.Observer;

/**
 * 实现java.util.Observer接口的观察者
 *
 */
public class Subscribe implements Observer {

    public Subscribe(Observable o){
        o.addObserver(this);        //将该观察者放入待通知观察者里
    }
    /* (non-Javadoc)
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("收到通知:" + ((Publish)o).getData());
    }

}