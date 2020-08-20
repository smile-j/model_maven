package com.dong.base.desgin.entity;

import lombok.Data;

@Data
public class Fruit {

    private String name;
    private int price;

    public void pack(Bag bag){
        bag.pack();
    }

    public Fruit(String name,int price){
        this.price = price;
        this.name = name;
    }

    public void draw(){
        System.out.println(name+"--的价格--"+price);
    }
}
