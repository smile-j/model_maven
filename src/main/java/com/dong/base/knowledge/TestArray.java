package com.dong.base.knowledge;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.net.Socket;
import java.util.Objects;

/**
 * Created by Administrator on 2017/8/15.
 * 1. 方法重载;函数个数或者参数类别不同
 */

public class TestArray {

    @Test
    public void testArray(){
        String [] strs = new String[]{"aa","bb","cc"};
        addArray(strs,"dd");
        for(Object str:strs){
            System.out.println("main -----"+str);
        }
    }

    public void addArray(String[] objs,String obj){
       String [] strs2 = ArrayUtils.add(objs, obj);
            for(Object obOne:objs){
                System.out.println("-----"+obOne);
            }
        for(Object str:strs2){
            System.out.println("2 -----"+str);
        }

    }

//    public void testSocket(){
//        Socket socket = new Socket("");
//        socket.
//    }
    @Test
    public void testExcel(){

    }

}
