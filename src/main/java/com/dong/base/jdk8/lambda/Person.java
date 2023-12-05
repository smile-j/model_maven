package com.dong.base.jdk8.lambda;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class Person {

    private Integer id;
    private String name;
    /**
     * 逗号隔开
     */
    private String add;

    /**
     * 爱好
     */
    private List<String > games;
}
