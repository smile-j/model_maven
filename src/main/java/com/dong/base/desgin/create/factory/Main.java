package com.dong.base.desgin.create.factory;

import com.dong.base.desgin.create.factory.factoryMethod.AppleFactory;
import com.dong.base.desgin.create.factory.factoryMethod.FruitFactory;
import com.dong.base.desgin.create.factory.factoryMethod.OrangeFactory;
import com.dong.base.desgin.create.factory.simple.SimpleFactory;
import com.dong.base.desgin.entity.Fruit;

public class Main {

    public static void main(String [] args){
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
