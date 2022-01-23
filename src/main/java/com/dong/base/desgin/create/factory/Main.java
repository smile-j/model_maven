package com.dong.base.desgin.create.factory;

import com.dong.base.desgin.create.factory.factoryMethod.AppleFactory;
import com.dong.base.desgin.create.factory.factoryMethod.FruitFactory;
import com.dong.base.desgin.create.factory.factoryMethod.OrangeFactory;
import com.dong.base.desgin.create.factory.simple.SimpleFactory;
import com.dong.base.desgin.entity.Fruit;

public class Main {

    public static void main(String [] args){
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
