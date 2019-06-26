package com.dong.base.test.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/23.
 * 1)使用transient关键字不序列化某个变量
 * 2)transient关键字只能修饰变量，而不能修饰方法和类
 * 3)被transient关键字修饰的变量不再能被序列化，一个静态变量不管是否被transient修饰，均不能被序列化。
 */
public class TransientEntity implements Serializable{

    private Integer id;
    private String name;

    private transient  String pwd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public  TransientEntity(){

    }
    public TransientEntity(Integer id, String name, String pwd) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "TransientEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
