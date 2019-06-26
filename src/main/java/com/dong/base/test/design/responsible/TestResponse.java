package com.dong.base.test.design.responsible;

import org.junit.Test;

/**
 * Created by Administrator on 2018/2/23.
 *
 * 责任链模式
 */
public class TestResponse {

    @Test
    public void test1(){
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();
        Handler handler3 = new ConcreteHandler3();
        handler1.setNextHandler(handler2);
        handler2.setNextHandler(handler3);
        Response response = handler1.handleRequest(new Request(new Level(3)));
    }

}
