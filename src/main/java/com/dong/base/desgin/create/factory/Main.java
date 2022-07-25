package com.dong.base.desgin.create.factory;

import com.dong.base.desgin.create.factory.factoryMethod.AppleFactory;
import com.dong.base.desgin.create.factory.factoryMethod.FruitFactory;
import com.dong.base.desgin.create.factory.factoryMethod.OrangeFactory;
import com.dong.base.desgin.create.factory.simple.SimpleFactory;
import com.dong.base.desgin.entity.Fruit;
import com.google.common.collect.Lists;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String [] s = in.nextLine().split(" ");
        int sum = Integer.parseInt(s[0]);
        int num = Integer.parseInt(s[1]);
        int [] w = new int[num+1];
        int [] v = new int[num+1];
        for(int i=1;i<=num;i--){
            String str = in.nextLine();
            String [] ss = str.split(" ");
            if("0".equals(ss[2])){
                w[i] = w[i]+Integer.parseInt(ss[0]);
                v[i] = v[i]+Integer.parseInt(ss[1]);
            } else {
                int index = Integer.parseInt(ss[2]);
                w[index] = w[index]+Integer.parseInt(ss[0]);
                v[index] = v[index]+Integer.parseInt(ss[1]);
            }
        }
        int n =sum/10+1;
        int m = num+1;
        int [][] vv= new int[m][n];
        int [][] path= new int[m][n];
        for(int i=1;i<m;i++){
//            sumd[][]
            for (int j=1;i<n;j++){

            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;i++){
//                sumd[i][j]=
            }

        }

    }
    public static void main2(String [] args){
        Integer auth = 0B000;
        Integer auth1 = 0B010;
        Integer auth2 = 0B100;
        Integer auth3 = 0B001;
        System.out.println(auth);
        System.out.println(auth1);
        System.out.println(auth2);
        System.out.println(auth3);
        System.out.println("=========================");
        Object o = new Object();
        int h;
        int num = (o == null) ? 0 : (h = o.hashCode()) ^ (h >>> 16);

        System.out.println("======="+num);

//        Fruit fruit = getFruitBySimpleFactoy();
        Fruit fruit = getFruitByFactoryMethod();
        fruit.draw();

    }

    public static Fruit getFruitBySimpleFactoy(){
        return SimpleFactory.getFruit(SimpleFactory.TYPE_ORANGE);
    }
    public static Fruit getFruitByFactoryMethod(){
        FruitFactory fruitFactory = null;
        fruitFactory = new AppleFactory();
        return fruitFactory.getFruit();
    }

}
