package com.dong.base.test.design.responsible;

/**
 * Created by Administrator on 2018/2/23.
 */
public class ConcreteHandler1 extends Handler{

    protected Level getHandlerLevel() {
        return new Level(1);
    }
    public Response response(Request request) {
        System.out.println("-----请求由处理器1进行处理-----");
        return null;
    }

}

class ConcreteHandler2 extends Handler {
    protected Level getHandlerLevel() {
        return new Level(3);
    }
    public Response response(Request request) {
        System.out.println("-----请求由处理器2进行处理-----");
        return null;
    }
}
class ConcreteHandler3 extends Handler {
    protected Level getHandlerLevel() {
        return new Level(5);
    }
    public Response response(Request request) {
        System.out.println("-----请求由处理器3进行处理-----");
        return null;
    }
}
