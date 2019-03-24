package com.dong.base.test.baseTest;

public class Outter {

    private int age = 1;
    public static String name ="outClass";

    public Outter(){

    }

    public  Inner getInner(){
        return new Inner();
    }

    public Outter(int age){
        this.age = age;
    }
    private class Inner{
        public void drawSahpe() {
            System.out.println(age);  //外部类的private成员
            System.out.println(name);   //外部类的静态成员
        }
    }

    public static void main(String args []){

        //内部类的创建方法

        //第一种方式：
        Outter outter = new Outter();
        Outter.Inner inner = outter.new Inner();  //必须通过Outter对象来创建

        //第二种方式：
        Outter.Inner inner1 = outter.getInner();

    }

}
