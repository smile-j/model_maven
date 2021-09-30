package com.dong.base.test;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/**
 * Created by Administrator on 2018/1/18.
 */
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("........................start...........");
        try {
       String inputStream = servletContextEvent.getServletContext().getInitParameter("log4jConfigLocation");
//       Properties prop = new Properties();
//
//        prop.load(inputStream);
//
//        Object obj  = prop.get("cloudUrl");
        System.out.println("..................................."+inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("........................end............");
    }
}
