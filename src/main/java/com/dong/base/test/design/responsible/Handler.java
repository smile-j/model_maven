package com.dong.base.test.design.responsible;

/**
 * Created by Administrator on 2018/2/23.
 */
public abstract class Handler {

    private Handler nextHandler;
    public final Response handleRequest(Request request){
        Response response = null;
        if(this.getHandlerLevel().above(request.getLevel())){
            response = this.response(request);
        }else{
            if(this.nextHandler != null){
                this.nextHandler.handleRequest(request);
            }else{
                System.out.println("-----没有合适的处理器-----");
            }
        }
        return response;
    }

    public void setNextHandler(Handler handler){
        this.nextHandler = handler;
    }
    protected abstract Level getHandlerLevel();
    public abstract Response response(Request request);

}

class Response{

}