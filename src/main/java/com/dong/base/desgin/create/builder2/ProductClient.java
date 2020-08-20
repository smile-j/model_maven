package com.dong.base.desgin.create.builder2;

public class ProductClient {

    public static void main(String [] args){

        Product3 p3 = new Product3.Builder()
                .id(10)
                .name("phone")
                .price(100)
                .type(1)
                .build();

    }

}
