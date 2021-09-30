package com.dong.base.jdk8.lambda;

import com.dong.base.jdk8.TestInterface;
import com.dong.base.jdk8.imp.TestInterfaceImpl;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;

/**
 * Created by Administrator on 2017/11/10.
 */
public class Lambda {

    @Test
    public void testOne(){
        //1.
        Arrays.asList("a", "b", "d").forEach( ( String e ) -> System.out.println( "e:"+e ) );
        Arrays.asList("a", "b", "d").forEach(System.out::println);
        //2.
//        Arrays.asList("a","b","c").forEach(e->{
//            System.out.println(" sencond:"+e);
//        });
        //3.
        Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> {
            int result = e1.compareTo( e2 );
            return result;
        } );
        //4.
        Comparator<Integer> com = (x,y)->Integer.compare(x,y);
    }

    @Test
    public void testThread(){

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("name:"+1);
            }
        };

        Runnable runnable1=()->{System.out.println("fff");} ;

       Thread thread = new Thread(runnable1);
        thread.start();
    }

    @Test
    public void testInterface(){
        int num=1;
        TestInterfaceImpl testInterface = new TestInterfaceImpl() ;
        double result =  testInterface.sqrt2(2);
        testInterface.defaultMethod();
        TestInterface.staticMethod();
//        TestInterfaceImpl
        System.out.println("result:"+result);
        TestInterface testInterface1= (aa)->{
            aa=aa+1;
            return aa*aa;
        };
        num++;
       System.out.println( "haha"+testInterface1.sqrt2(11));

    }
    @Test
    public void testFunction(){
        String name = "";
        String name1 = "12345";
        System.out.println(validInput(name, inputStr -> inputStr.isEmpty() ? "名字不能为空":inputStr));
        System.out.println(validInput(name1, inputStr -> inputStr.length() > 3 ? "名字过长":inputStr));
    }

    public static String validInput(String name,Function<String,String> function) {
        return function.apply(name);
    }



}
