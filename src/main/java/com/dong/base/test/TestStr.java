package com.dong.base.test;


import com.dong.base.model.UserEntity;
import com.dong.base.model.UserEntity2;
import com.dong.base.test.reflect.Student;
import com.dong.base.test.thread.model.Person2;
import org.junit.Test;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.security.Timestamp;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2018/1/24.
 */
public class TestStr {

    class Person implements Cloneable{
        String name;
        int age;
        int num=0;
        UserEntity userEntity;


        public Person clone(){
            Person p = null;
            try {
               p = (Person) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return p;
        }

        public Person(){}
        public Person(String name,int age){
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            if(userEntity!=null){
                return "Person{" +
                        "name='" + name + '\'' +
                        ", age=" + age +
                        ", num=" + num +
                        ", userEntity=" + userEntity +
                        '}';
            }
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", num=" + num +
                    '}';
        }



        public void testThread(){
            num++;
            System.out.println("name:"+Thread.currentThread().getName()+" num:"+num);
        }
    }

    private static void change(StringBuffer s,String arg,int aa,Person p) {
        s.append("sb");
        arg = "ssssssssss";
        aa = 11;
        p.name ="sssssssss";
    }

    @Test
    public void testString(){
//        String arg = new String("www.tiantianbianma.com");
        Person pp = new Person("sbsb",11);
        UserEntity userEntity = new UserEntity();
        userEntity.setId(11);userEntity.setPwd("abc");
        pp.userEntity = userEntity;
        Person pp2 = pp.clone();
        System.out.println("*************************  "+(pp==pp2)+"  "+pp.equals(pp2));
        pp2.name="123";
        pp2.userEntity.setPwd("aaaa");
        System.out.println(pp+"*************************  "+pp2);

        StringBuffer s = new StringBuffer("111");
        String arg = "www.tiantianbianma.com";
        int aa =10;
        change(s,arg,aa,pp);
        System.out.println(s+"   "+arg+"    "+aa+"       "+pp);
    }

//    @Test
//    public void test2(){
    public static void main(String[] args){
        final Person2 pp = new Person2("sbsb",11);
        final CountDownLatch countDownLatch = new CountDownLatch(10);
        try {


        //1，自定义线程
       /* for (int i=0;i<10;i++){
           new Thread(new Runnable() {
               @Override
               public void run() {
                   pp.testThread();
                   countDownLatch.countDown();
               }
           }).start();
        }
        try {
            countDownLatch.await();//等待所有工人完成工作
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        //2. 利用线程池
        ExecutorService executor = Executors.newScheduledThreadPool(10);
       for (int i=0;i<10;i++){
           executor.submit(new Runnable() {
               @Override
               public void run() {
                   pp.testThread();
               }
           });
       }
        executor.shutdown();
//        executor.awaitTermination(100, TimeUnit.MILLISECONDS);不正确的写法

            while (!executor.awaitTermination(100, TimeUnit.MILLISECONDS)){
                System.out.println("线程等待！！！");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(countDownLatch.getCount()+"   result:"+pp.num);
    }

    @Test
    public void testCodeSort(){
//        UserEntity2 us = new UserEntity2();
//        UserEntity2 us2 = new UserEntity2();
        try {
            Class clas= Class.forName("com.bbs.common.model.UserEntity2");
            Constructor con = clas.getConstructor(UserEntity2.class);
            UserEntity2 user = (UserEntity2) con.newInstance();
            System.out.println("---------"+user);


//          Class clas= Class.forName("com.bbs.common.model.UserEntity2");
           //Class.forName(className);
           //实际上是调用的是：
           //Class.forName(className, true, this.getClass().getClassLoader());//第二个参为true即默认类需要初始化，初始化会触发目标对象静态块的执行和静态变量的初始化

//            ClassLoader.loadClass("com.bbs.common.model.UserEntity2");
           Class claz =  this.getClass().getClassLoader().loadClass("com.bbs.common.model.UserEntity2");
            Object obj = claz.newInstance();
            System.out.println(obj.toString());
            //实际上调用的是:
            //ClassLoader.loadClass(name, false);//第二个参数即默认得到的class还没有进行链接，意味着不进行初始化等系列操作，即静态代码块不会执行
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testIntern() throws Throwable {
//
//        Class.forName("com.bbs.common.model.UserEntity2", true, this.getClass().getClassLoader());
        String baseStr = "baseStr";
        final String baseFinalStr = "baseStr";

        String str1 = "baseStr01";
        String str2 = "baseStr"+"01";
        String str3 = baseStr + "01";
        String str4 = baseFinalStr+"01";
        String str5 = new String("baseStr01").intern();

        System.out.println(str1 == str2);//#3
        System.out.println(str1 == str3);//#4
        System.out.println(str1 == str4);//#5
        System.out.println(str1 == str5);//#6
        System.out.println(baseStr == baseFinalStr);//#6
        finalize();
    }


    @Test
    public void testIntern2() {
        String str1 = "baseStr01";
        String str2 = "baseStr"+"01";
        String str3 = new String("baseStr01");
        String str4 =str3.intern();
        System.out.println(str1 == str2);//#3
        System.out.println(str1 == str3);//#4
        System.out.println(str2 == str3);//#5
        System.out.println(str1 == str4);//#4
    }

    @Test
    public void testQuote() {

        Student s=new Student();
        s.setName("Lucy");
        s.setAge(12);
        s.setAddress("北京");
//        WeakReference<Student> reference=new WeakReference<Student>(s);//<span style="color:#FF0000;">关联强引用</span>
        //软引用（SoftReference）: 如果一个对象具有软引用，内存空间足够，垃圾回收器就不会回收它
        //如果内存空间不足了，就会回收这些对象的内存。只要垃圾回收器没有回收它，该对象就可以被程序使用。
        SoftReference reference=new SoftReference(s);
        System.out.println("1:    "+reference.get());
        System.gc();
        System.out.println("gc:   "+reference.get());

    }


}
