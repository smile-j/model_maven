package com.dong.base.jdk8;

import org.junit.Test;

/**
 * Created by Administrator on 2018/1/7.
 */
public class TestSort {

    static int max=3;
    static boolean[] flag= new boolean[max];
    static int[] data = new int[max];

    public void dfs(int pos){
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

}
