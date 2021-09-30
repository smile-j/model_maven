package com.dong.base.jdk8.lambda;

import com.dong.base.jdk8.MyFun;
import com.dong.base.jdk8.model.Employee;
import org.junit.Test;


import java.util.*;
import java.util.function.Consumer;

/**
 * 一、lambda 表达式的基础语法：Java8中引入了一个新的操作符"->" 该操作符为箭头操作或者lambda操作符
 *
 * 左侧：Lambda表达式的参数列表
 * 右侧：Lambda表达式中所需执行的功能，即Lambda体
 *
 * 语法格式一：无参数，无返回值
 *      ()->System.out.println(" hello Lambda!");
 * 语法格式二：有一个参数，无返回值
 *      (x)->System.out.println(x);
 * 语法格式三：若只有一个参数，小括号可以忽略不写
 *        x->System.out.println(x);
 * 语法格式四：有俩个以上的参数，且有返回值，并且lambdat体中有多条语句
 *         Comparator<Integer> com = (x,y)->{
 *            System.out.println("函数式接口！");
 *              return Integer.compare(x,y);
 *             };
 * 语法格式五：若lambda体中只有一条语句，return和大括号都可以省略不写
 *         Comparator<Integer> com = (x,y)->Integer.compare(x,y);
 *
 * 语法格式六：lambda表达式的参数列表的数据类型可以省略不写（类型推断）
 *             Comparator<Integer> com = (Integer x, Integer y)->Integer.compare(x,y);
 *
 *
 * 二、Lambda表达式需要“函数式接口”的支持
 * 函数式接口：接口中只有一个抽象方法的接口，称为函数式接口  可以使用注解@FunctionalInterface修饰
 *              可以检查是否函数式接口
 */
public class TestLambda2 {

    @Test
    public void test1(){
        int num=0;//jdk1.7前  必须final  内部类
        Runnable runable = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world!"+num);//还是不能改变  默认加上了final
            }
        };
        runable.run();
        System.out.println("--------------------------------------------");

        Runnable runable2 =()->System.out.println("hello Lambda!" + num);
        runable2.run();

    }

    @Test
    public void test2(){
//        Consumer<String> con = (x)->System.out.println(x);
        Consumer<String> con = x->System.out.println(x);
        con.accept("哈哈哈，美好一天！");
    }

    @Test
    public void test3(){
        Comparator<Integer> com = (x,y)->{
          System.out.println("函数式接口！");
            return Integer.compare(x,y);
        };
       Integer result = com.compare(9, 8);
        System.out.println("result:"+result);
    }

    @Test
    public void test4(){
        Comparator<Integer> com = (x,y)->Integer.compare(x,y);
        Integer result = com.compare(9, 8);
        System.out.println("result:"+result);
    }
    @Test
    public void test5(){
        List<String> list= new ArrayList<>();
        show(new HashMap<>());
    }

    public void show(Map<String,Integer> map){

    }

    //对一个数进行计算
    @Test
    public void test6(){
       Integer result= opeation(100,(x->x*x));
        System.out.println("结果："+result);
    }
    public Integer opeation(Integer num,MyFun my){
        return my.getValue(num);
    }
    /**********************************************************/
    List<Employee> emps = Arrays.asList(
            new Employee(101, "张三", 18, 9999.99),
            new Employee(102, "李四", 59, 6666.66),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(105, "田七", 38, 5555.55)
    );
    @Test
    public void test7(){
        Collections.sort(emps,(e1,e2)->{
            if(e1.getAge()==e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            }else{
                return Integer.compare(e1.getAge(),e2.getAge());
            }
        });
        for (Employee emp:emps){
            System.out.println(emp);
        }
    }
}
