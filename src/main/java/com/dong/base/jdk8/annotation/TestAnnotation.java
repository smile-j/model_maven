package com.dong.base.jdk8.annotation;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * 重复注解和类型注解
 */
public class TestAnnotation {

    //checker framework
    public /*@Nonnull*/ Object obj =null;

    @Test
    public void test1() throws NoSuchMethodException {
        Class<TestAnnotation> clazz = TestAnnotation.class;
        Method m1 = clazz.getMethod("show");

       MyAnnotation[] mas = m1.getAnnotationsByType(MyAnnotation.class);

       for(MyAnnotation my:mas){
           System.out.println(my.value());
       }
    }

    @MyAnnotation("Hello")
    @MyAnnotation("World")
    public void show(@MyAnnotation("abc")String str){

    }

}
