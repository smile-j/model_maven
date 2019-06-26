package com.dong.base.model;

/**
 * Created by Administrator on 2020/12/1.
 */
public class InnerExample {

    public String name ;

    public void outAdd(){
        System.out.println("outAdd....");
    }

    public class Inner {

        public String userName="inner Name";

        public void innerAdd(){
            System.out.println(userName+"   inneradd...."+name);
        }
    }


    public Inner getInner(){
        return new Inner();
    }

}
