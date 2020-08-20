package com.dong.base.jdk8.lambda;

import com.dong.example.User;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by Administrator on 2017/11/14.
 */
public class TestLambda {


    //1. Consumer  一听这名字就知道是消费某个对象，没有返回值。
    /**
     * 一个简单的平方计算
     */
    @Test
    public void testConsumer(){
        //设置好Consumer实现方法
        Consumer<Integer> square= x->System.out.println("seq:"+x*x);
        //传入值
        square.accept(2);
    }

    /**
     * 定义3个Consumer并按顺序进行调用andThen方法
     */
    @Test
    public  void testAndThen() {
        //当前值
        Consumer<Integer> consumer1 = x -> System.out.println("当前值 : " + x);
        //相加
        Consumer<Integer> consumer2 = x -> { System.out.println("相加 : " + (x + x)); };
        //相乘
        Consumer<Integer> consumer3 = x -> System.out.println("相乘 : " + x * x);
        //andThen拼接
        consumer1.andThen(consumer2).andThen(consumer3).accept(1);
    }

    //2. Supplier  提前定义可能返回的一个指定类型结果，等需要调用的时候再获取结果。

    @Test
    public void testSupplier(){
        User user = null;
        //先判断son是否为null,如果为不为null则返回当前对象,如果为null则返回新创建的对象
        User optional = Optional.ofNullable(user).orElseGet(() -> new User());
        System.out.println(optional);
    }

    //3. Function 实现一个”一元函数“，即传入一个值经过函数的计算返回另一个值
    /**
     * 1、apply 示例
     */
    @Test
    public void applyTest(){
        //示例1：定义一个funciton,实现将String转换为Integer
        Function<String, Integer> function = x -> Integer.parseInt(x);
        Integer a = function.apply("100");
        System.out.println(a.getClass());
        // 结果：class java.lang.Integer
    }
    /**
     * 2、andThen 示例
     * 组合函数，调用当前function之后调用
     */
    @Test
    public  void andThenTest() {
        //示例2：使用andThen() 实现一个函数 y=10x + 10;
        //先执行 10 * x
        Function<Integer, Integer> function2 = x -> 10 * x;
        //通过andThen在执行 这里的x就等于上面的10 * x的值
        function2 = function2.andThen(x -> x + 10);
        System.out.println(function2.apply(2));
        //结果：30

    }

    /**
     * 3、compose 示例
     * 组合函数，调用当前function之前调用
     */
    @Test
    public  void composeTest() {
        //示例3：使用compose() 实现一个函数 y=(10+x)2;
        Function<Integer, Integer> function3 = x -> x * 2;
        //先执行 x+10 在执行(x+10)*2顺序与上面相反
        function3 = function3.compose(x -> x + 10);
        System.out.println(function3.apply(3));
        //结果：26
    }

    /**
     * 4、综合示例
     */
    public  void test() {

//示例4：使用使用compose()、andThen()实现一个函数 y=(10+x)*2+10;
        //执行第二步
        Function<Integer, Integer> function4 = x -> x * 2;
        //执行第一步
        function4 = function4.compose(x -> x + 10);
        //执行第三步
        function4 = function4.andThen(x -> x + 10);
        System.out.println(function4.apply(3));
        //结果：36

    }
    public void jdkdemo(){
        Map<String, List<String>> map = new HashMap<>();
        List<String> list;

// java8之前写法
        list = map.get("key");
        if (list == null) {
            list = new LinkedList<>();
            map.put("key", list);
        }
        list.add("11");

// 使用 computeIfAbsent 可以这样写 如果key返回部位空则返回该集合 ，为空则创建集合后返回
        list = map.computeIfAbsent("key", k -> new ArrayList<>());
        list.add("11");
    }
}
