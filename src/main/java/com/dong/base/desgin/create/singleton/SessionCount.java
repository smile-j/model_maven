package com.dong.base.desgin.create.singleton;

import java.util.concurrent.atomic.AtomicInteger;

public class SessionCount {

    private AtomicInteger count = new AtomicInteger(0);

    private SessionCount(){

    }
    private static SessionCount sessionCount;
    public  static SessionCount getInstance() {
       if(sessionCount==null){
           synchronized (SessionCount.class){
               if(sessionCount==null){
                   sessionCount = new SessionCount();
               }
           }
       }
       return sessionCount;
    }

    /***以下是业务方法***/
    public int plus(){
        return count.incrementAndGet();
    }

    public int decrease(){
        return count.decrementAndGet();
    }

    public void showMessage(){
        System.out.println("当前人数："+this.count.get());
    }
}
