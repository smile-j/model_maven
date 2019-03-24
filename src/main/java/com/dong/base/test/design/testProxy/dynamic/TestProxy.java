package com.dong.base.test.design.testProxy.dynamic;


import com.dong.base.test.design.testProxy.dynamic.cglib.BookFacadeCglib;
import com.dong.base.test.design.testProxy.dynamic.cglib.BookFacadeImpl1;
import com.dong.base.test.design.testProxy.dynamic.cglib.BookFacadeImpl2;
import com.dong.base.test.design.testProxy.dynamic.decorator.DecoratorTest;
import com.dong.base.test.design.testProxy.dynamic.entity.Book;
import com.dong.base.test.design.testProxy.dynamic.jdkproxy.BookFacade;
import com.dong.base.test.design.testProxy.dynamic.jdkproxy.BookFacadeImpl;
import com.dong.base.test.design.testProxy.dynamic.jdkproxy.BookFacadeProxy;
import org.junit.Test;
import org.springframework.aop.framework.ProxyFactoryBean;

import java.lang.reflect.*;
import java.lang.reflect.InvocationHandler;

/**
 * Created by Administrator on 2018/1/4.
 * java动态代理是利用反射机制生成一个实现代理接口的匿名类，在调用具体方法前调用InvokeHandler来处理。
 而cglib动态代理是利用asm开源包，对代理对象类的class文件加载进来，通过修改其字节码生成子类来处理
 如何强制使用CGLIB实现AOP？
 （1）添加CGLIB库，SPRING_HOME/cglib/*.jar
 （2）在spring配置文件中加入<aop:aspectj-autoproxy proxy-target-class="true"/>
 *
 *
 */
public class TestProxy {

    @Test
    public void testProxy(){

        final BookFacade bookFacade = new BookFacadeImpl();

        /**
         * ClassLoader loader,
         *Class<?>[] interfaces,
         *InvocationHandler h
         */
        BookFacade proxy = (BookFacade)Proxy.newProxyInstance(BookFacade.class.getClassLoader(), new Class[]{BookFacade.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("欢迎使用....."+method.getName());
//                Object obj = proxy.getClass().newInstance();
               Object resutl = method.invoke(bookFacade,args);
                System.out.println("结束....."+method.getName());
                return resutl;
            }
        });
        System.out.println(BookFacadeImpl.getBooks());
        proxy.addBook(new Book("005","骆驼祥子","老舍先生"));
        System.out.println(BookFacadeImpl.getBooks());
    }

    @Test
    public void testJdkProxy(){//JdkDynamicAopProxy
        BookFacadeProxy proxy = new BookFacadeProxy();
        BookFacade bookProxy = (BookFacade) proxy.bind(new BookFacadeImpl());
        System.out.println(BookFacadeImpl.getBooks());
        bookProxy.addBook(new Book("005","骆驼祥子","老舍先生"));
        System.out.println(BookFacadeImpl.getBooks());

        System.out.println(BookFacadeImpl.getBooks());
        boolean bl = bookProxy.updateBook(new Book("002", "BBB", "BBB"));
        System.out.println(bl);
        System.out.println(BookFacadeImpl.getBooks());

        System.out.println(BookFacadeImpl.getBooks());
        String result = bookProxy.deleteBook("001");
        System.out.println(result);
        System.out.println(BookFacadeImpl.getBooks());
    }

    /**
     * cglib原理：
     * 运行时动态的生成一个被代理类的子类（通过ASM字节码处理框架实现），子类重写了被代理类中所有非final的方法。在子类中采用方法拦截的技术拦截所有父类方法的调用，顺势植入横切逻辑
     *
     * 优点：JDK动态代理要求被代理的类必须实现接口，当需要代理的类没有实现接口时Cglib代理是一个很好的选择。另一个优点是Cglib动态代理比使用java反射的JDK动态代理要快（Cglib的FastClass机制，解析参考http://www.cnblogs.com/cruze/p/3865180.html#lable3）
     *缺点：对于被代理类中的final方法，无法进行代理，因为子类中无法重写final函数
     */
    @Test
    public void TestCglib(){//CglibAopProxy
        BookFacadeCglib cglib=new BookFacadeCglib();
        BookFacadeImpl1 bookCglib=(BookFacadeImpl1)cglib.getInstance(new  BookFacadeImpl1());
        bookCglib.addBook();

        System.out.println(BookFacadeImpl1.getBooks());
        boolean bl = bookCglib.updateBook2(new Book("002", "BBB", "BBB"));
        System.out.println(bl);
        System.out.println(BookFacadeImpl1.getBooks());
    }

    @Test
    public void test1(){
        //final  方法不能重新  可以继承  ；fianl 类 不能继承
        BookFacadeImpl2 bookFacadeImpl2 = new BookFacadeImpl2();
        bookFacadeImpl2.updateBook(new Book("002", "BBB", "BBB"));
    }

    @Test
    public void testDecorator(){
       BookFacade bookFacade = new BookFacadeImpl();
        DecoratorTest decoratorTest = new DecoratorTest(bookFacade);
        decoratorTest.addBook(new Book("003","XXXX","XXXX"));
    }


    @Test
    public void testAop(){
        BookFacade bookFacade = new BookFacadeImpl();
        //2.创建ProxyFactoryBean,用以创建指定对象的Proxy对象
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        //3.设置Proxy的接口
        proxyFactoryBean.setInterfaces(BookFacade.class);
        //4. 设置RealSubject
        proxyFactoryBean.setTarget(bookFacade);
        //5.使用JDK基于接口实现机制的动态代理生成Proxy代理对象，如果想使用CGLIB，需要将这个flag设置成true
        proxyFactoryBean.setProxyTargetClass(true);
//6. 添加不同的Advice

//        proxyFactoryBean.addAdvice(afterReturningAdvice);
//        proxyFactoryBean.addAdvice(aroundAdvice);
//        proxyFactoryBean.addAdvice(throwsAdvice);
//        proxyFactoryBean.addAdvice(beforeAdvice);
//        proxyFactoryBean.setProxyTargetClass(false);
        System.out.println(BookFacadeImpl.getBooks());
        BookFacade targetBookFacade = (BookFacade) proxyFactoryBean.getObject();
        targetBookFacade.updateBook(new Book("002", "BBB", "BBB"));
        System.out.println(BookFacadeImpl.getBooks());

    }
}
