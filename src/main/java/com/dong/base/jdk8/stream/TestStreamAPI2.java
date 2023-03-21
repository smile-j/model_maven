package com.dong.base.jdk8.stream;

import com.alibaba.fastjson.JSON;
import com.dong.base.jdk8.model.DeptVo;
import com.dong.base.jdk8.model.Employee;
import com.google.common.collect.Sets;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 一、Stream 的三个操作步骤
 *  1.创建Stream
 *  2.中间操作
 *  3.终止操作(终端操作)
 *
 */
public class TestStreamAPI2 {
//中间操作
    List<Employee> emps = Arrays.asList(
            new Employee(101, "张三", 18, 9999.99),
            new Employee(102, "李四", 59, 5555.55),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 36, 7777.77),
            new Employee(105, "田七1", 12, 8888.88),
            new Employee(106, "田七2", 12, 8888.88),
            new Employee(107, "田七4", 12, 8888.88),
            new Employee(108, "aa", 36, 8888.88),
            new Employee(109, "bb", 28, 8888.88)
    );
    //分组
    @Test
    public void testGroup(){

        Collection<Integer> intersection = CollectionUtils.intersection(Sets.newHashSet(1, 2), Sets.newHashSet(4, 5, 3));
        System.out.println("=============>"+ JSON.toJSONString(intersection.s));


        List<DeptVo> deptVos = null;
        List<Employee> collect5 = deptVos.stream().flatMap(e -> e.getUsers().stream()).collect(Collectors.toList());

        Map<Integer, List<Employee>> collect = emps.stream().collect(Collectors.groupingBy(Employee::getAge));

        Map<Integer, List<Integer>> collect4 = emps.stream().collect(Collectors.groupingBy(Employee::getAge, Collectors.mapping(Employee::getId, Collectors.toList())));

        Map<Integer, Employee> collect1 = emps.stream().collect(Collectors.toMap(Employee::getId, Function.identity()));
        Map<Integer, Employee> collect2 = emps.stream().collect(Collectors.toMap(Employee::getId, Function.identity(),(v1,v2)->v1));
        Map<Integer, String> collect3 = emps.stream().collect(Collectors.toMap(Employee::getId, Employee::getName));


    }

    //中间操作
    /**
     * 排序
     * sorted()--自然顺序排序(Comparable)
     sorted(Comparator comp)--定制排序(Comparator)
     */
    @Test
    public void test7(){
        List<String> list = Arrays.asList("ccc","aaa","bbb","ddd","eee");

        list.stream()
                .sorted()
                .forEach(System.out::println);
        System.out.println("---------------------------------------------------");

        emps.stream()
                .sorted((e1, e2) -> {
                    System.out.println("sb");
                    if (e1.getAge().equals(e2.getAge())) {
                        return e1.getName().compareTo(e2.getName());
                    } else {
                        int res = e1.getAge().compareTo(e2.getAge());
                        System.out.println(e2.getAge()+"---"+e2.getAge()+" result"+res);
                        return res;
                    }
                }).forEach(System.out::println);
    }


    /**
     *映射
     * map(Function f)-- Lambda，将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
     * flatMap(Function f) -- 接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */
    @Test
    public void test6(){
        List<String> list = Arrays.asList("aaa","bbb","ccc","ddd","eee");
        list.stream()
                .map((str)->str.toUpperCase())
                .forEach(System.out::println);
        System.out.println("------------------------------------------");
        emps.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
        System.out.println("------------------------------------------");

//        Stream<Stream<Character>> stream = list.stream()
//                .map(TestStreamAPI2::filterCharacter);
//        stream.forEach((stm)->{
//            stm.forEach(System.out::println);
//        });
        System.out.println("------------------------------------------");
        Stream<Character> sm = list.stream()
                .flatMap(TestStreamAPI2::filterCharacter);
        // <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);
        sm .forEach(System.out::println);
    }
    public static Stream<Character> filterCharacter(String str){
        List<Character> list = new ArrayList<>();
        for(Character ch:str.toCharArray()){
            list.add(ch);
        }
        return list.stream();
    }

    /***
     * 筛选与切片
     filter(Predicate p)--接收 Lambda ， 从流中排除某些元素。
     distinct()--筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
     limit(long maxSize)--截断流，使其元素不超过给定数量。
     skip(long n)--跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
     */
    @Test
    public void test5(){
        emps.stream()
                .filter((e) -> e.getSalary() > 5000)
                .skip(2)
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    public void test4(){
        emps.stream()
              .filter((e)->e.getSalary()>5000)
              .skip(2)
              .forEach(System.out::println);
    }

    @Test
    public void test3(){
        emps.stream()
                .filter((e)->{
                    System.out.println("短路！");//&&  ||
                    return e.getSalary()>5000;
                })
                .limit(2)
                .forEach(System.out::println);
    }

    //内部迭代:迭代操作由Stream API 完成
    @Test
    public void test1(){
        //中间操作：不会执行任何操作
        Stream<Employee> stream = emps.stream()
                .filter((e)->{
                    System.out.println("Stream API 的中间操作！");
                    return e.getAge()>35;
                });

        //终止操作:一次性执行全面内容，即”惰性求值“
        stream.forEach(System.out::println);
    }

    //外部迭代
    @Test
    public void test2(){
        Iterator<Employee> it = emps.iterator();

        while (it.hasNext()){
            System.out.println(it.next());
        }

    }

}
