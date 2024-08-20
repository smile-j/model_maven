package com.dong.base.jdk8.fun_interface;



import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *  Predicate接口主要用来判断一个参数是否符合要求
 */
public class Test_Predicate {

    /*@FunctionalInterface
    public interface Predicate<T> {
        *//**
         * 具体过滤操作 需要被子类实现.
         * 用来处理参数T是否满足要求,可以理解为 条件A
         *//*
        boolean test(T t);
        *//**
         * 调用当前Predicate的test方法之后再去调用other的test方法,相当于进行两次判断
         * 可理解为 条件A && 条件B
         *//*
        default Predicate<T> and(Predicate<? super T> other) {
            Objects.requireNonNull(other);
            return (t) -> test(t) && other.test(t);
        }
        *//**
         * 对当前判断进行"!"操作,即取非操作，可理解为 ! 条件A
         *//*
        default Predicate<T> negate() {
            return (t) -> !test(t);
        }
        *//**
         * 对当前判断进行"||"操作,即取或操作,可以理解为 条件A ||条件B
         *//*
        default Predicate<T> or(Predicate<? super T> other) {
            Objects.requireNonNull(other);
            return (t) -> test(t) || other.test(t);
        }

        *//**
         * 对当前操作进行"="操作,即取等操作,可以理解为 A == B
         *//*
        static <T> Predicate<T> isEqual(Object targetRef) {
            return (null == targetRef)
                    ? Objects::isNull
                    : object -> targetRef.equals(object);
        }
    }*/


    @Test
    public void testSimple(){

        /**
         * 1、判断数字是否大于7
         */
        //设置一个大于7的过滤条件
        Predicate<Integer> predicate = x -> x > 7;
        System.out.println(predicate.test(10)); //输出 true
        System.out.println(predicate.test(6));  //输出 fasle
        /**
         * 2、大于7并且
         */
        //在上面大于7的条件下，添加是偶数的条件
        predicate = predicate.and(x -> x % 2 == 0);
        System.out.println(predicate.test(6));  //输出 fasle
        System.out.println(predicate.test(12)); //输出 true
        System.out.println(predicate.test(13)); //输出 fasle
        /**
         * 3、add or 简化写法
         */
        predicate = x -> x > 5 && x < 9;
        System.out.println(predicate.test(10)); //输出 false
        System.out.println(predicate.test(6));  //输出 true

    }


    @Test
    public void testStream(){

        User user1 = new User("张三", "女", 1);
        User user2 = new User("李四", "男", 2);
        User user3 = new User("张三", "女", 3);
        List<User> list = Lists.newArrayList(user1, user2, user3);

        /**
         * 1、获取年龄大于2的对象
         * 2.性别筛选
         */
        Predicate<User> predicate1 = user ->user.getAge()>2;
        Predicate<User> predicate2 = user ->"女".equals(user.getSex());
        List<User> collect1 = list.stream()
                .filter(user ->user.getAge()>2 && "女".equals(user.getSex()))
                .collect(Collectors.toList());
        List<User> collect2 = list.stream()
                .filter(predicate1.and(predicate2))
                .collect(Collectors.toList());

        System.out.println("获取年龄大于2&&女 " + JSON.toJSONString(collect1));
        System.out.println("获取年龄大于2&&女 " + JSON.toJSONString(collect2));

    }

    @Test
    public void testGuavaPredicate(){

    }
}
