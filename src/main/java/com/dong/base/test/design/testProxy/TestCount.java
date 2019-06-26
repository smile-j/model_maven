package com.dong.base.test.design.testProxy;

import org.junit.Test;

/**
 * Created by Administrator on 2018/1/4.
 */
public class TestCount {

    @Test
    public void TestCount(){
        CountImpl countImpl = new CountImpl();
        CountProxy countProxy = new CountProxy(countImpl);
        countProxy.updateCount();
        System.out.println("*****************************************");
        countProxy.queryCount();
    }

}
