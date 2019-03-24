package com.dong.base.test.jvm;

import org.junit.Test;

/**
 * Created by Administrator on 2017/11/27.
 */
public class TestJVm {

    @Test
    public void test1(){
        Object obj = new Object();
//        SoftReference<Object> sf = new SoftReference<Object>(obj);
        Object[] objs = new Object[]{obj};
        obj = null;
//        System.gc();
//        System.out.println(sf.get());//有时候会返回null
        System.out.println(objs[0]);//有时候会返回null
    }
}
