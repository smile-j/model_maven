package com.dong.base.test.gc;

import org.springframework.cglib.proxy.CallbackFilter;
import org.springframework.cglib.proxy.Dispatcher;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class OOM {



    public static void main(String [] args){
        testMetaSpaceOomMock();
    }

    /**
     * 1.-XX:MaxPermSize=10m
     * -XX:PermSize=10m
     * -Xms100m
     * -Xmx100m
     * -XX:-UseGCOverheadLimit
     *
     * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     */
    public static void  testStr(){
        try {
            List<String> list = new ArrayList<String>();
            for (int i = 0; ; i++) {
                System.out.println(i);
                list.add(String.valueOf("String" + i++).intern());
            }
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *-XX:MetaspaceSize=8m
     * -XX:MaxMetaspaceSize=50m
     *
     * Caused by: java.lang.OutOfMemoryError: Metaspace
     */
    public static void testMetaSpaceOomMock(){
        ClassLoadingMXBean loadingBean = ManagementFactory.getClassLoadingMXBean();
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOM.class);
            enhancer.setCallbackTypes(new Class[]{Dispatcher.class, MethodInterceptor.class});
            enhancer.setCallbackFilter(new CallbackFilter() {
                @Override
                public int accept(Method method) {
                    return 1;
                }

                @Override
                public boolean equals(Object obj) {
                    return super.equals(obj);
                }
            });

            Class clazz = enhancer.createClass();
            System.out.println(clazz.getName());
            //显示数量信息（共加载过的类型数目，当前还有效的类型数目，已经被卸载的类型数目）
            System.out.println("total: " + loadingBean.getTotalLoadedClassCount());
            System.out.println("active: " + loadingBean.getLoadedClassCount());
            System.out.println("unloaded: " + loadingBean.getUnloadedClassCount());
        }
    }

}
