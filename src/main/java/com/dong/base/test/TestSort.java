package com.dong.base.test;

import org.junit.Test;

import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Administrator on 2018/1/7.
 */
public class TestSort {

    static int max=4;
    static boolean[] flag= new boolean[max];
    static int[] data = new int[max];

    //递归
    public void dfs(int pos){
        //使用CAS来实现，是非阻塞式的“lock-free”实现
        ConcurrentLinkedQueue concurrentLinkedQueue;
        //ArrayBlockingQueue和LinkedBlockingQueue都是使用lock来实现的，也就是阻塞式的队列
        ArrayBlockingQueue arrayBlockingQueue;LinkedBlockingQueue linkedBlockingQueue;
        if(pos ==max){
            for(int d:data){
            System.out.print(d + ",");
            }
            System.out.println();
            return;
        }
        for(int i=0;i<max;i++){
            if(flag[i]==false){
                flag[i]=true;
                data[pos] = i+1;
                dfs(pos+1);
//                sort1(pos:pos+1);
//                sort1(pos+1);
                flag[i]=false;
            }
        }
    }

    @Test
    public  void test1(){
        dfs(0);
    }


    /**
     * 斐波那契数列为:0,1,1,2,3,5...

     fib(0)=0;

     fib(1)=1;

     fib(n)=fib(n-1)+fib(n-2);
     * @param n
     * @return
     */
    //这是递归
    int funcA(int n)
    {
      if(n>1)
          return funcA(n - 1) + funcA(n - 2);
     else
          return n; // n = 0, 1时给出recursion终止条件
    }
    //这是迭代
    int funcB(int n)
    {
        int i, temp0 =0, temp1, temp2;
        if(n<=1) return n;
        temp1 = 0;
        temp2 = 1;
        for(i = 2; i <= n; i++){
            temp0 = temp1 + temp2;
            temp2 = temp1;
            temp1 = temp0;
        }
        return temp0;
    }

    //迭代是人，递归是神
    @Test
    public void testAA(){
       System.out.println(funcA(2));
       System.out.println(funcB(2));
    }

    @Test
    public void testSort(){

        int s=0;
        String output="";
        int a =5;
        //No.1
        //开始写代码，请根据input实现左侧需求中的问题。
        for(int i =1;i<=a;i++){
            System.out.println("*********"+"55555".substring(0,i));
            s+=Integer.parseInt("55555".substring(0, i));


        }
        System.out.println(s);


//        //end_code
//        System.out.println(s);
//
//        int a=11,b=12,aa;
//        System.out.println(a+"   ----------  "+b);
//        a=a+b;
//        b=a-b;
//        a=a-b;
//        System.out.println(a+"   ----------  "+b+aa);
    }


}
