package com.dong.base.jdk8.model;

import lombok.Data;

import java.util.List;

@Data
public class DeptVo {
    private Integer id;
    private String name;
    private List<Employee> users;
}
