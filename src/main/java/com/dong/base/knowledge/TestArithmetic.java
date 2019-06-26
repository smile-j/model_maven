package com.dong.base.knowledge;

import org.junit.Test;

/**
 * Created by Administrator on 2017/8/23.
 */
public class TestArithmetic {



    /**
     * 冒泡法
     */
    @Test
    public void testArray(){
       int [] scores  = {19,11,13,18,12,14,17,15,16};
        System.out.print("排序前:");
        for(int i=0;i<scores.length;i++){
            System.out.print("*" + scores[i]);
        }
      for(int i=0;i<scores.length;i++){
          for(int j=i+1;j<scores.length;j++){
              if(scores[i]>scores[j]){
                  int tem = scores[i];
                  scores[i] = scores[j];
                  scores[j]=tem;
              }
          }

      }
        System.out.print("\n排序后:");
        for(int i=0;i<scores.length;i++){
            System.out.print("*"+scores[i]);
        }
    }

    /**
     * 选择排序
     */
    @Test
    public void testChoice(){
        int [] scores  = {19,11,13,18,12,14,17,15,16};
        System.out.print("排序前:");
        for(int i=0;i<scores.length;i++){
            System.out.print("*" + scores[i]);
        }
        for(int i=0;i<scores.length;i++){
            int minIndex = i;
            for(int j=i+1;j<scores.length;j++){
                if(scores[i]>scores[j]){
                    minIndex = j;
                }
            }
            if(i!=minIndex){
                int tem = scores[minIndex];
                scores[minIndex] = scores[i];
                scores[i] = tem;
            }

        }
        System.out.print("\n排序后:");
        for(int i=0;i<scores.length;i++){
            System.out.print("*"+scores[i]);
        }
    }


}
