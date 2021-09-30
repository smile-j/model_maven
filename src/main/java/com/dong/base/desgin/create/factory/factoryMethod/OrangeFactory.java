package com.dong.base.desgin.create.factory.factoryMethod;

import com.dong.base.desgin.entity.Fruit;
import com.dong.base.desgin.entity.Orange;

public class OrangeFactory implements FruitFactory {
    @Override
    public Fruit getFruit() {
        return new Orange();
    }
}
