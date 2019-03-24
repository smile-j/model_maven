package com.dong.base.netty;

/**
 * Created by Administrator on 2018/5/29.
 */
public class PipedInputStreamAndPipedOutputStream {

    public static void main(String[] args) {
        Send s = new Send();
        Receive r = new Receive();

        try {
            s.getPos().connect(r.getPis());   //连接两个线程的管道流

        } catch (Exception e) {
            e.printStackTrace();
        }

        new Thread(s).start();
        new Thread(r).start();
    }

}
