package com.dong.base.model;

import lombok.Builder;
import lombok.Data;

@Data
public class OrderEntity {

    private String id;
    private UserEntity user;
    private String desc;

}
