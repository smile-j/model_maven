package com.dong.base.desgin.structure.bridge;

import com.dong.base.desgin.structure.bridge.bag.BagAbstraction;
import com.dong.base.desgin.structure.bridge.bag.SmallBag;
import com.dong.base.desgin.structure.bridge.material.Material;
import com.dong.base.desgin.structure.bridge.material.Paper;
import com.dong.base.desgin.structure.bridge.material.Sack;

public class BridgeClient {

    public static void main(String[] args) {

        //袋子型号
        BagAbstraction bag = new SmallBag();

        //袋子材质
        Material material = new Sack();
        bag.setMaterial(material);

        //开始采摘
        bag.pick();
    }

}
