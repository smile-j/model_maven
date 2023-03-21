package com.dong.base.test.design;


/**
 * 23种设计模式
 * 创造型（5种）：工厂方法、抽象工厂、单例、建造者、原型模式
 * 结构型（7种）：适配器、装饰器、代理、外观、桥接、组合、享元
 * 行为型（11种）：策略、模板方法、观察者、迭代子模式、责任链、命令、备忘录、状态模式、访问者、中介者、解释器
 *
 * 6大原则：
 *  1.开闭原则：对扩展开放，对修改关闭
 *  2.里氏替换原则：任何基类可以出现的地方，子类一定可以出现。里氏代换原则是对“开-闭”原则的补充。
 *        实现“开-闭”原则的关键步骤就是抽象化。而基类与子类的继承关系就是抽象化的具体实现
 *  3.依赖倒转：这个是开闭原则的基础，具体内容：真对接口编程，依赖于抽象而不依赖于具体。
 *  4.接口隔离：这个原则的意思是：使用多个隔离的接口，比使用单个接口要好。还是一个降低类之间的耦合度的意思
 *  5.迪米特法则（最少原则）：一个实体应当尽量少的与其他实体之间发生相互作用，使得系统功能模块相对独立。
 *  6.合成服用原则：尽量使用合成/聚合的方式，而不是使用继承
 *
 */
public class TestDesign {

//    @Test
//    public void testSignleton(){
    public static void main(String[] args){
           for (int i=0;i<100;i++){
               final int num = i;
               new Thread(new Runnable() {
                   @Override
                   public void run() {
                       System.out.println(Thread.currentThread().getName()+"-------------"+(num+1)+"     "+2);
                       System.out.println("result: "+Thread.currentThread().getName()+"   "+Singleton.getInstance().add(num+1,2));
                   }
               },i+"").start();
           }

    }

}
