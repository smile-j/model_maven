package com.dong.base.desgin.create.factory.abstractFactory;

import com.dong.base.desgin.entity.Apple;
import com.dong.base.desgin.entity.Bag;
import com.dong.base.desgin.entity.Fruit;
import com.dong.base.desgin.entity.Orange;
import com.dong.base.desgin.entity.bag.AppleBag;
import com.dong.base.desgin.entity.bag.OrangeBag;

public class OrangeFactory extends AbstractFactory {
    @Override
    public Fruit getFruit() {
        return new Orange();
    }

    @Override
    public Bag getBag() {
        return new OrangeBag();
    }
}
