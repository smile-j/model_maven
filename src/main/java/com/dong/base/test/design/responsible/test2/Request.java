package com.dong.base.test.design.responsible.test2;

//封装请求的类Request
public class Request {
    String requestStr;

    public String getRequest() {
        return requestStr;
    }

    public void setRequest(String request) {
        this.requestStr = request;
    }
}
//封装响应信息的类Response
 class Response {
    String responseStr;

    public String getResponse() {
        return responseStr;
    }

    public void setResponse(String response) {
        this.responseStr = response;
    }

}
