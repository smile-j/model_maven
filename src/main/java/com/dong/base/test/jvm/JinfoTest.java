package com.dong.base.test.jvm;

import java.io.IOException;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/9/26
 */
public class JinfoTest {

    /**
     * 命令:jinfo -flag name pid
     * 擂讙·使用该命令﹔可以查看指定的jvm参数的值
     * 如:查看当前jvm进程是否开后打印GC日志
     * jinfo -flag PrintGc pid
     *
     * 命令:jinfo -flag [ +/ -]name pid
     * 描远:开启或者关闭对应名称的参数
     * 使用jinfo可以在不重后虚拟机的情况下﹐可以动态的修改 jvm 的参数·尤其在线上的环境特别有用·
     * jinfo -fLag +printGc pid
     * jinfo -fLag PrintGc pid
     * jinfo -fLag +PrintGc pid
     * jinfo -flag printoc pid
     * 命令:jinfo -flag name=value pid
     * 描证：修改指定参数的值
     * 和上面的例子相似，但是上面的主要是针对boolean信的参数设置的
     * 如果是设置value值，则需要使用name=vaLue的形式
     * jinfo -flag HeapDumppath pid
     *
     * jinfo -fLag HeapDumpPath=d:\\dump pid
     * jinfo -fLag HeapDumpPath pid
     * jinfo -flag HeapDumppath= pid
     * jinfo -flag HeapDumpPath pid
     *
     * 注意∶jinfo虽然可以在java程序运行时动态地修改虚拟机参数﹐但并不是所有的参数都支持动态修改
     *
     *
     */

    public static void main(String[] args) throws IOException {

        System.out.println("jinfo");
        System.in.read();

    }

}
