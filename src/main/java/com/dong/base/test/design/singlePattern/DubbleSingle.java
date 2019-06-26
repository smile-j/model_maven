package com.dong.base.test.design.singlePattern;

public class DubbleSingle {

    private static DubbleSingle ds ;

    public static DubbleSingle getDs(){
        if (ds == null){
            try {
                //模拟初始化对象的准备时间
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            synchronized (DubbleSingle.class){
                if (ds == null){
                    ds = new DubbleSingle();
                }
            }
        }
        return ds;
    }

}
