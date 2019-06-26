package com.dong.base.netty;

import java.io.PipedOutputStream;

/**
 * Created by Administrator on 2018/5/29.
 */
public class Send implements Runnable{
    private PipedOutputStream pos = null;

    public Send(){
        this.pos = new PipedOutputStream();     //实例化管道输出流
    }
    @Override
    public void run() {
        String str = "zhejianggongshangdaxue";
        try {
            this.pos.write(str.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.pos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public PipedOutputStream getPos(){   //通过线程类获得管道输出流
        return pos;
    }
}
