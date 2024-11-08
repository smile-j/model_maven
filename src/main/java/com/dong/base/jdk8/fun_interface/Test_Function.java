package com.dong.base.jdk8.fun_interface;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.function.Function;

public class Test_Function {

    /**
     *     Function<T,R> 从注解中我们可以了解到：Function
     *     这个接口它接收一个参数并且产生一个结果，也就是第一个参数类型 T 代表入参，
     *     第二个参数类型 R 代表返回的结果。
     *     这两个参数交给了它的函数式方法 apply(）来处理，apply() 实现的逻辑就是接收参数类型 T 然后返回参数类型 R，也就是将 T 类型的数据转换为了 R 类型的数据。
     *
     *
     */

    @Test
    public void test(){

        User user1 = new User("张三", "女", 1);
        User user2 = new User("李四", "男", 2);
        User user3 = new User("张三", "女", 3);
        List<User> list = Lists.newArrayList(user1, user2, user3);
        printField(list,User::getName);
        printField(list,User::getSex);

    }
    void printField(List<User> users, Function<User,String> function){
        users.stream().map(function).forEach(System.out::println);
    }

    @Test
    public void composeTest(){
        /**
         * compose 这个方法会先执行 before 的逻辑，再执行它自己的逻辑，然后只要有一个有异常，都会给调用者。
         * 也就是说它会先执行入参的逻辑，再把它的结果当作自己的入参，再处理自己的逻辑，最后把自己的逻辑给返回回去。
         * 这是一种嵌套执行的操作，顺序是由里到外，但值得注意的是，compose 和 before 这两个Function 都是操作，
         * 所以还需要再调一个 apply() 方法来接收一个源数据，然后才能进行这一些列的处理
         */
        //使用compose() 实现一个函数 y=(10+x)2;
        Function<Integer,Integer> fun1 = x -> 10 + x;
        Function<Integer,Integer> fun2 = x -> 2 * x;

        System.out.println(fun2.compose(fun1).apply(2));

    }


    /**
     * 和 compose 类似，只不过执行的顺序是相反的
     * 且 andThen 和其入参的 Function 这两个的入参和结果也需要相对应才行。
     */
    @Test
    public void testAndThen(){
//        使用使用compose()、andThen()实现一个函数 y=(10+x)*2+15;

        //执行第二步
        Function<Integer, Integer> function1 = x -> x + 10;
        Function<Integer, Integer> function2 = x -> x * 2;
        Function<Integer, Integer> function3 = x -> x + 15;

        //由外到内
        System.out.println(function2.andThen(function3).apply(3));//21

        System.out.println(function2.compose(function1).andThen(function3).apply(3));//41
        System.out.println(function2.andThen(function3).compose(function1).apply(3));//41

        //结果：36
    }

}
