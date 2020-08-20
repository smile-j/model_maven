package com.dong.base.desgin.create.factory.abstractFactory;

import com.dong.base.desgin.entity.Bag;
import com.dong.base.desgin.entity.Fruit;

public abstract class AbstractFactory {

    public abstract Fruit getFruit();
    public abstract Bag getBag();

}
