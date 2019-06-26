package com.dong.base.test.concurrent;

import javassist.bytecode.Descriptor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2018/2/5.
 */
public class TestVolatile {

    private List<String> list = new ArrayList<String>();
    private volatile boolean  canGet = false;

    public void  put(){
        for (int i=0;i<10;i++){
            list.add("A");
            System.out.println(Thread.currentThread().getName()+"添加了 "+list.size()+" 个元素");
            if(i==5){
                canGet = true;
                System.out.println("线程"+Thread.currentThread().getName()+"发出通知！");
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }

        }
    }

    public void get(){
        while (true){
            if(canGet){
                System.out.println(Thread.currentThread().getName()+"  :"+list.size()+"   "+list.toString());
                break;
            }

        }
    }

    public static void main(String[] args){

        //model 1
      /*  final TestVolatile testVolatile =   new TestVolatile();
        new Thread(new Runnable() {
            @Override
            public void run() {
                testVolatile.put();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                testVolatile.get();
            }
        }).start();*/

        //model 2.

        ThreadDeomo threadDeomo = new ThreadDeomo();

        new Thread(threadDeomo).start();
        while (true){
            if(threadDeomo.getFlag()){
                System.out.println("-----------------------------------");
                break;
            }
/*            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

              while (true){
                synchroize(threadDeomo){
                  if(threadDeomo.getFlag()){
                System.out.println("-----------------------------------");
                break;
                }
            }

            }
            */
        }

    }


    static class ThreadDeomo implements Runnable{

        private volatile boolean flag ;

        public boolean getFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        @Override
        public void run() {
            flag =true;
            System.out.println("flag="+getFlag());
        }
    }

}
