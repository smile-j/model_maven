package com.dong.base.jdk8.stream;

import com.dong.base.jdk8.model.Employee;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 终止操作
 */
public class TestStreamAPI3 {

    List<Employee> emps = Arrays.asList(
            new Employee(101, "张三", 18, 9999.99, Employee.Status.FREE),
            new Employee(102, "李四", 59, 5555.55,Employee.Status.BUSY),
            new Employee(103, "王五", 28, 3333.33,Employee.Status.VOCATION),
            new Employee(104, "赵六", 36, 7777.77,Employee.Status.FREE),
            new Employee(105, "田七", 12, 8888.88,Employee.Status.BUSY),
            new Employee(105, "田七", 59, 8888.88,Employee.Status.BUSY)
    );

    /**
     * 收集
     collect(Collector c) 将流转换为其他形式。接收一个 Collector接口的实现，用于给Stream中元素做汇总的方法
     */

    @Test
    public void test10(){
        String strs = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(",","========","========"));
        System.out.println(strs);
    }

    @Test
    public void test9(){
        DoubleSummaryStatistics ds = emps.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));

        System.out.println(ds.getSum());
        System.out.println(ds.getAverage());
        System.out.println(ds.getMax());
    }

    //分区
    @Test
    public void test8(){
        Map<Boolean,List<Employee>> map =emps.stream()
                .collect(Collectors.partitioningBy((e)->e.getSalary()>8000));
        System.out.println(map);
    }

    //多级分组
    @Test
    public void test7(){
     Map<Employee.Status,Map<String,List<Employee>>> map = emps.stream()
                .collect(Collectors.groupingBy(Employee::getStatus,Collectors.groupingBy((e)->{
                    if(e.getAge()<=35){
                        return "青年";
                    }else if (e.getAge()<=50){
                        return "中年";
                    }else{
                        return "老年";
                    }
                })));

        emps.stream().collect(Collectors.toMap(Employee::getStatus
                ,entry-> Arrays.stream(entry.getName().split(","))
                        .collect(Collectors.toMap(e->e.length(), Function.identity()))));
        System.out.println(map);
    }

    //分组
    @Test
    public void test6(){
        Map<Employee.Status,List<Employee>> map =emps.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));

        System.out.println(map);

    }

    @Test
    public void test5(){
        //总数
        Long count = emps.stream()
                .collect(Collectors.counting());

        //平均值  工资的
        Double avg = emps.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));

        Double sum = emps.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));

        Optional<Employee> max = emps.stream()
                    .collect(Collectors.maxBy((e1,e2)->Double.compare(e1.getSalary(),e2.getSalary())));

        Optional<Double> min = emps.stream()
                                    .map(Employee::getSalary)
                                    .collect(Collectors.minBy(Double::compare));

    }

    @Test
    public void test4(){
        //搜集所有员工的名字
        emps.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
        System.out.println("--------------------------------------------------");
        emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet())
                .forEach(System.out::println);
        System.out.println("--------------------------------------------------");
        emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new))
                .forEach(System.out::println);
    }

    /**
     * 归约
     * reduce(T iden, BinaryOperator b) 可以将流中元素反复结合起来，得到一个值。返回 T
     reduce(BinaryOperator b) 可以将流中元素反复结合起来，得到一个值。返回 Optional<T>
     */

    @Test
    public void test3(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
       Integer sum = list.stream()
                .reduce(0,(x,y)->x+y);
        System.out.println(sum);
        System.out.println("----------------------------------");
        Optional<Double> op = emps.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(op.get());
    }

    /**
     * allMatch(Predicate p) 检查是否匹配所有元素
     anyMatch(Predicate p) 检查是否至少匹配一个元素
     noneMatch(Predicate p) 检查是否没有匹配所有元素
     findFirst() 返回第一个元素
     count() 返回流中元素总数
     max(Comparator c) 返回流中最大值
     min(Comparator c) 返回流中最小值
     */
    @Test
    public void test2(){
        Long count = emps.stream().count();
        System.out.println(count);

        Optional<Employee> e = emps.parallelStream()
                                    .max((e1, e2) -> {
                                        return Double.compare(e1.getSalary(), e2.getSalary());
                                    });
        System.out.println(e);

        Optional<Double> salary = emps.parallelStream()
                .map(Employee::getSalary)
                .min(Double::compare);
        System.out.println(salary);

    }

    @Test
    public void test1(){
       boolean result1 = emps.stream()
                .allMatch((e)->e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(result1);

        boolean result2 = emps.stream()
                .anyMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(result2);

        boolean result3 = emps.stream()
                .noneMatch((e)->e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(result3);

        Optional<Employee> optional =emps.stream()
                .sorted((e1,e2)->Double.compare(e1.getSalary(),e2.getSalary()))
                .findFirst();
        optional.orElse(new Employee(11));
        System.out.println(optional.get());

//        Optional<Employee> ep2 = emps.stream()
        Optional<Employee> ep2 = emps.parallelStream()
                .filter((e)->e.getStatus().equals(Employee.Status.FREE))
                .findAny();
        System.out.println(ep2.get());

    }

}
