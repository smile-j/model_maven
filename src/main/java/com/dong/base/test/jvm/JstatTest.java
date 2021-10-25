package com.dong.base.test.jvm;

import java.io.IOException;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/9/26
 */
public class JstatTest {

    //-Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:+PrintGCDetails -verbose:gc
    public static void main(String[] args) throws IOException {
        final int _1MB = 1024*1024;
        byte [] b1 = new byte[2*_1MB];
        System.out.println("1.....");
        System.in.read();

        byte[] b2 = new byte[2*_1MB];
        System.out.println("2.....");
        System.in.read();

        byte[] b3 = new byte[2*_1MB];
        System.out.println("3....");
        System.in.read();

        byte[] b4 = new byte[2*_1MB];
        System.out.println("4....");
        System.in.read();
        byte[] b5 = new byte[2*_1MB];
        System.out.println("5....");
        System.in.read();
        byte[] b6 = new byte[2*_1MB];
        System.out.println("6....");
        System.in.read();
    }


}
