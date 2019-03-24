package com.dong.base.test.reflect;

import org.junit.Test;

import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * Created by Administrator on 2018/3/2.
 */
public class Reflect_One {

    //@Before
    public void beforeOne(){
        System.out.println("开始！！！");
    }

    //@After
    public void afterTwo(){
        System.out.println("结束！！！");
    }

    @Test
    public void  testIntrospector(){
        Class clazz=Person.class;
        Method[] methods=clazz.getMethods();//不能获取私有的方法private
        Method [] methods2=clazz.getDeclaredMethods();//获取当前类所有方法，且包括私有的
        for(Method method:methods){
            System.out.println(method);
        }
      Field [] fields =  clazz.getFields();

      System.out.println("******size:"+fields.length);
    }

    /***
     * 关于Class:
     * 1.Class 是一个里类  由系统创建
     * 类的属性：Field
     * 类的方法：Method
     * 类的构造器：	Constrctor
     * @throws NoSuchMethodException
     * @throws Exception
     */
    @Test
    public void testMethod() throws Exception{
        Class clazz=Student.class;
        Method [] methods=clazz.getMethods();//不能获取私有的方法private
        Method [] methods2=clazz.getDeclaredMethods();//获取当前类所有方法，且包括私有的
        System.out.println("公有的："+methods.length+"加上私有的："+methods2.length);
        for(Method method:methods2){
            System.out.println("1方法名："+method.getName());
        }
        MethodDescriptor[] md= Introspector.getBeanInfo(clazz).getMethodDescriptors();
        System.out.println("方法个数md:"+md.length);
        for(MethodDescriptor mdr:md){
            System.out.println("2方法名："+mdr.getName());
        }
        //获取指定的方法
//		Method me=clazz.getDeclaredMethod("test2",String.class,Integer.class);
//		System.out.println(me.getName());
//		Person p=new Person();
//		Object obj= clazz.newInstance();
//		me.invoke(obj, "张三",11);
//		System.out.println("1:"+clazz+">>>>"+clazz.getClass());
    }
    @Test
    public void testClass() {
        Class clazz = null;

        //1.得Class对象
        //1.1 直接通过类名.class获得
        clazz=Person.class;
        //1.2 通过对象调用getClass（）方法获取
        Person p=new Person();
        clazz = p.getClass();
        //1.3 通过全类名的方式获取
        String className="com.bbs.common.test.reflect.Person";
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Person stu=new Student();
        System.out.println("父类还是子类："+stu.getClass());
        Field[] fieds=clazz.getDeclaredFields();
        System.out.println(fieds);
        System.out.println(clazz);
    }

    @Test
    public void testNewInstance() throws Exception{

        String className="reflect.model.Person";
        Class clazz=Class.forName(className);
//        clazz = Person.class;
//        clazz = new Person().getClass();

        Object obj = clazz.newInstance();
        System.out.println(obj.getClass());

    }

    /**
     * 类加载器：
     * 引到类加载器；扩张类加载器；系统类加载器
     * @throws ClassNotFoundException
     * @throws IOException
     */
    @Test
    public void testClassLoader() throws ClassNotFoundException, IOException, IOException {

        //String te = Reflect_One.class.getResource("test1.properties").getPath();
        //System.out.println(">>>>"+te);

        //1. 获取一个系统的类加载器  Launcher$AppClassLoader
        //ClassLoader classLoader = 	ClassLoader.getSystemClassLoader();
        //System.out.println(classLoader);
        //System.out.println(classLoader.getClass());
        //2. 获取系统类加载器的父类加载器     Launcher$ExtClassLoader
        //classLoader= classLoader.getParent();
        //System.out.println(classLoader);
        //3. 获取扩展加载器的父类加载器
        //classLoader=classLoader.getParent();
        //System.out.println(classLoader);
        //4. 测试当前类的加载器
        //classLoader= Class.forName("reflect.Reflect_One").getClassLoader();
        //System.out.println("当前类的加载器："+classLoader);
        //5.jdk的类加载器
//        ClassLoader  classLoader= Class.forName("java.lang.Object").getClassLoader();
//        System.out.println("JDK的加载器："+classLoader);
    }
        //6. 关于类加载器的方法
        public void testClassLoader2() throws ClassNotFoundException, IOException, IOException {
        InputStream in =null;
        //new FileInputStream("test.properties");

        //reflect/test1.properties
        //in=this.getClass().getClassLoader().getResourceAsStream("test.properties");
        in=this.getClass().getResourceAsStream("test1.properties");

        //2.
        // String templetPath = Student.class.getResource("/").getPath();
        // System.out.println("路径："+templetPath);
        // File file =new File(templetPath+"test.properties");
        // in=new FileInputStream(file);
        System.out.println("文件："+in);
        Properties pro= new Properties();
        pro.load(in);
        System.out.println("名字："+pro.getProperty("name"));
    }


}
