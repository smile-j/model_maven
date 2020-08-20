package com.dong.base.desgin.create.factory.simple;

import com.dong.base.desgin.entity.Apple;
import com.dong.base.desgin.entity.Banana;
import com.dong.base.desgin.entity.Fruit;
import com.dong.base.desgin.entity.Orange;

public class SimpleFactory {

     public  static final int TYPE_APPLE=1;
    public  static final int TYPE_BANANA=2;
    public static final int TYPE_ORANGE=3;


    public static Fruit getFruit(int type){
        Fruit fruit = null;
        switch (type){

            case TYPE_APPLE:{fruit = new Apple();break;}
            case TYPE_BANANA:{fruit = new Banana();break;}
            case TYPE_ORANGE:{fruit = new Orange();break;}
            default:{
                fruit = new Fruit("未知水果",111111);
            }

        }

        return fruit;

    }



}
