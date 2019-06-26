package com.dong.base.jdk8;

/**
 * Created by Administrator on 2017/11/12.
 */
@FunctionalInterface
public interface TestInterface2 {

    double calculate(int a);
//    double calculate2(int a);

    default double sqrt(int a) {
        return Math.sqrt(a);
    }

//    default double sqrt2(int a) {
//        return Math.sqrt(a);
//    }

    static void staticMethod() {
        System.out.println("接口中的静态方法2");
    }
    // default修饰符定义默认方法
    default  void defaultMethod() {
        System.out.println("接口中的默认方法2");
    }
}
