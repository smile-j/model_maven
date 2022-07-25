package com.dong.example.tyfx;

import java.util.concurrent.TimeUnit;

/**
 * 逃逸分析
     *  -Xms4G -Xmx4G -XX:-DoEscapeAnalysis -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
 *  -Xms4G -Xmx4G -XX:+PrintGCDetails -XX:-DoEscapeAnalysis -XX:+HeapDumpOnOutOfMemoryError
 *
 *  -XX:+PrintGC -Xms20M -Xmn20M -XX:+DoEscapeAnalysis
 *
 *jps                # 查看进程   jps -l 包含包名
 * jmap -histo 2809   # 查看对象个数与分布情况
 * jstack 2809        # 查看堆栈信息
 *
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000_000; i++) {
            alloc();
        }
        long end = System.currentTimeMillis();
        System.out.println("cost: " + (end-start) + " ms");
        // 睡眠一下，让我们观察一下 堆 栈 中对象的数量
        TimeUnit.MINUTES.sleep(1);
    }

    private static void alloc() {
        new Object();
    }


}
