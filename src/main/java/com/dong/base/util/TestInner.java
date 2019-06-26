package com.dong.base.util;

import com.dong.base.model.InnerExample;
import com.dong.base.model.Outer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2020/12/1.
 */
public class TestInner {

    public static void main(String [] args){
        InnerExample example = new InnerExample();
        example.name="OutClass";

        InnerExample.Inner inner = example.getInner();
        InnerExample.Inner inner2 = example.new Inner();
        inner.innerAdd();

        List list = Collections.synchronizedList(new LinkedList<>());
        ArrayList loi = new ArrayList();
//        loi.add();loi.

//        String bl = "111";
//        switch (bl){//byte short int char
//            case "111":{ break;}
//            case "2222":{break;}
//            default:{
//
//            }
//        }
    }

}
