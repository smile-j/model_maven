package com.dong.base.netty;

import java.io.PipedInputStream;

/**
 * Created by Administrator on 2018/5/29.
 */
public class Receive implements Runnable{

    private PipedInputStream pis = null;
    public Receive(){
        this.pis = new PipedInputStream();   //实例化管道输入流
    }
    @Override
    public void run() {
        byte b[]=new byte[1024];
        int len= 0;
        try {
            len = this.pis.read(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            pis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("接收内容："+new String(b,0,len));
    }
    public PipedInputStream getPis(){         //通过线程类获得管道输入流
        return pis;
    }

}
