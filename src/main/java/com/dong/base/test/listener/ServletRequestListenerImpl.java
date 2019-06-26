package com.dong.base.test.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * Created by Administrator on 2018/3/19.
 */
public class ServletRequestListenerImpl implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        System.out.println("....................servletRequestEvent..............start");
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        System.out.println("....................servletRequestEvent..............end");

    }
}
