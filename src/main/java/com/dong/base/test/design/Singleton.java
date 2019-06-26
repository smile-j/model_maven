package com.dong.base.test.design;

/**
 * Created by Administrator on 2018/6/13.
 */
public class Singleton {

    private Singleton() {
        System.out.println("单利、、、、、、、、、");
    }
    private static final Singleton single = new Singleton();
    //静态工厂方法
    public static Singleton getInstance() {
        return single;
    }

    public int add(int a,int b){
//        System.out.println("a:"+a+" b:"+b);
        int num=1;
        while (num++<6){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println("............." + Thread.currentThread().getId());
        }
       return a+b;
    }
}
