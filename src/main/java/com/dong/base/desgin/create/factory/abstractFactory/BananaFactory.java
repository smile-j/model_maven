package com.dong.base.desgin.create.factory.abstractFactory;

import com.dong.base.desgin.entity.Apple;
import com.dong.base.desgin.entity.Bag;
import com.dong.base.desgin.entity.Banana;
import com.dong.base.desgin.entity.Fruit;
import com.dong.base.desgin.entity.bag.AppleBag;
import com.dong.base.desgin.entity.bag.BananaBag;

public class BananaFactory extends AbstractFactory {
    @Override
    public Fruit getFruit() {
        return new Banana();
    }

    @Override
    public Bag getBag() {
        return new BananaBag();
    }
}
