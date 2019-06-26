package com.dong.base.test.design.iterator;

public class IteratorTest {

    /**
     * 迭代器模式
     * @param args
     */
    public static void main(String[] args) {

        List list=new ConcreteAggregate();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        Iterator it=list.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }

}
