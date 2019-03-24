package com.dong.base.jdk8;

import com.dong.base.jdk8.model.Employee;
import com.dong.base.jdk8.model.Godness;
import com.dong.base.jdk8.model.NewMan;
import org.junit.Test;

import java.util.Optional;

/**
 *Optional.of(T t) : 创建一个 Optional 实例
 Optional.empty() : 创建一个空的 Optional 实例
 Optional.ofNullable(T t): 若 t 不为 null, 创建 Optional 实例, 否则创建空实例
 isPresent() : 判断是否包含值
 orElse(T t) : 如果调用对象包含值，返回该值，否则返回t
 orElseGet(Supplier s) : 如果调用对象包含值，返回该值，否则返回 s 获取的值
 map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
 flatMap(Function mapper): 与 map 类似，要求返回值必须是Optional
 */
public class TestOptional {

    @Test
    public void test4(){
        Optional<Employee> op = Optional.ofNullable(new Employee(110,"张三",12,9995.66,Employee.Status.FREE));

        Optional<String> str = op.map((e) -> e.getName());
        System.out.println(str.get());

       Optional<String> stringOptional = op.flatMap((e) -> Optional.of(e.getName()));
        System.out.println(stringOptional.get());

    }

    @Test
    public void test3(){
        Optional<Employee> op = Optional.ofNullable(null);

//        if(op.isPresent()){
//            System.out.println(op.get());
//        }
      /*  Employee ep = op.orElse(new Employee(110,"张三",12,9995.66,Employee.Status.FREE));
        System.out.println(ep);*/

        Employee ep = op.orElseGet(()->{
            return  new Employee();
        });
        System.out.println(ep);

    }

    @Test
    public void  test2(){
        Optional<Employee> op = Optional.empty();

        System.out.println(op.get());
    }

   @Test
    public void test1(){
       Optional<Employee> op = Optional.of(new Employee());

       System.out.println(op.get());
   }

    //应用练习
    @Test
    public  void  test5(){
        Optional<NewMan> op = Optional.ofNullable(null);
        String name =getGodnessName(op);
        System.out.println(name);
    }

    public String getGodnessName(Optional<NewMan> man){
        return man.orElse(new NewMan())
                .getGodness()
                .orElse(new Godness("苍老师"))
                .getName();
    }

}


