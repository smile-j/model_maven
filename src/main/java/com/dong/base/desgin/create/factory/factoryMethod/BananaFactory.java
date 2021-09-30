package com.dong.base.desgin.create.factory.factoryMethod;

import com.dong.base.desgin.entity.Banana;
import com.dong.base.desgin.entity.Fruit;

public class BananaFactory implements FruitFactory {
    @Override
    public Fruit getFruit() {
        return new Banana();
    }
}
