package com.dong.base.test.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * 反射
 *
 */
public class CreateObject {

    //com.dong.base.test.reflect
    public static void main(String[] args) {

        try {
            Student s = null;
            String classPath ="com.dong.base.test.reflect.Student";
//            s =  createByClass(classPath);
            s =  createByConstructor(classPath);
//            s.doAction();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static <T> T createByClass(String classPath) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        Class oClass = Class.forName(classPath);
        Object o = oClass.newInstance();
        Method doAction = oClass.getMethod("doAction");
        doAction.invoke(o);
        return (T)o;


    }

    public static <T> T createByConstructor(String classPath) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Class oClass = Class.forName(classPath);
        Constructor constructor = oClass.getConstructor();
        Constructor declaredConstructor = oClass.getDeclaredConstructor();
        Object o =  constructor.newInstance();
        Method doAction = oClass.getMethod("doAction");
        doAction.invoke(o);
        return (T)o;

    }

}
