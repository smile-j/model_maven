package com.dong.base.desgin.create.factory.abstractFactory;

import com.dong.base.desgin.entity.Apple;
import com.dong.base.desgin.entity.Bag;
import com.dong.base.desgin.entity.Fruit;
import com.dong.base.desgin.entity.bag.AppleBag;

public class AppleFactory extends AbstractFactory {
    @Override
    public Fruit getFruit() {
        return new Apple();
    }

    @Override
    public Bag getBag() {
        return new AppleBag();
    }
}
