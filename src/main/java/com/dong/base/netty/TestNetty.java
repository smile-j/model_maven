package com.dong.base.netty;

/**
 * Created by Administrator on 2018/5/29.
 */
public class TestNetty {

    //将规则跑起来
    public static void main(String[] args) throws Exception {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8080;
        }
        new DiscardServer(port).run();
        System.out.println("server:run()");
    }

}
