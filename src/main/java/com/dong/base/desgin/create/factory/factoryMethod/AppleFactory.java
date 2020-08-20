package com.dong.base.desgin.create.factory.factoryMethod;

import com.dong.base.desgin.entity.Apple;
import com.dong.base.desgin.entity.Fruit;

public class AppleFactory implements FruitFactory {
    @Override
    public Fruit getFruit() {
        return new Apple();
    }
}
