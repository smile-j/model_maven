package com.dong.base.test.entity;

import java.io.*;
import java.util.Date;

/**
 * Created by Administrator on 2018/1/23.
 * 1)使用transient关键字不序列化某个变量
 * 2)transient关键字只能修饰变量，而不能修饰方法和类
 * 3)被transient关键字修饰的变量不再能被序列化，一个静态变量不管是否被transient修饰，均不能被序列化。
 */
public class TransientEntity2 implements Externalizable{

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

    public TransientEntity2(){

    }
    public TransientEntity2(Integer id, String name, String pwd) {
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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("现在执行序列化方法");
        //可以在序列化时写非自身的变量
        Date d=new Date();
        out.writeObject(d);
        //只序列化userName,userPass变量
        out.writeObject(name);
        out.writeObject(pwd);
        out.writeObject(id);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("现在执行反序列化方法");
        Date d=(Date)in.readObject();
        System.out.println(d);
        this.name=(String)in.readObject();
        this.pwd=(String)in.readObject();
        this.id=(Integer)in.readObject();
    }
}
