package com.dong.base.test.design.visitor;

public class Client {

    /**
     * 访问者模式
     * @param args
     */
    public static void main(String[] args) {
        //创建一个结构对象
        ObjectStructure os = new ObjectStructure();
        //给结构增加一个节点
        os.add(new NodeA());
        //给结构增加一个节点
        os.add(new NodeB());
        //创建一个访问者
        Visitor visitor = new VisitorA();
        os.action(visitor);
        System.out.println("--------------------");
        Visitor visitorB = new VisitorB();
        os.action(visitorB);
    }

}
