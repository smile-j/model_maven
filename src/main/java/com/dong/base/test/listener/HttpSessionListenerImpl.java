package com.dong.base.test.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Enumeration;

/**
 * Created by Administrator on 2018/3/19.
 */
public class HttpSessionListenerImpl implements HttpSessionListener{

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("....................HttpSessionListener..............start");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("....................HttpSessionListener..............destroyed");
    }
}
