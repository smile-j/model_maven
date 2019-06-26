package com.dong.base.test.quartztest;

import org.junit.Test;

/**
 * Created by Administrator on 2020/12/1.
 */
public class SB {

    public static void  main(String [] args){
        System.out.println("***********:"+test1());
    }
    static int test1(){

        int x =1;
        try
        {
            return x;
        }/*catch (Exception e){
            System.out.println("*************exception");
            e.printStackTrace();
        }*/
        finally
        {
            ++x;
            System.out.println("$$$$$$$:"+x);
            return 3;
        }

    }
}
