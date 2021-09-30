package com.dong.base.test;

import org.junit.Test;

/**
 * Created by Administrator on 2017/9/12.
 */
public class TestOperator {

    @Test
    public void testOne() {
        int number = 10;
        //原始数二进制
        printInfo(number);
        number = number << 1;
        //左移一位
        printInfo(number);
        number = number >> 1;
        //右移一位
        printInfo(number);
    }


    private static void printInfo(int num) {
        System.out.println("\n二进制:"+ Integer.toBinaryString(num));
        System.out.println("十进制:"+num);
    }

}
