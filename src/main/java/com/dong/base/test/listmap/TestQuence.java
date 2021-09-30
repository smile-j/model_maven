package com.dong.base.test.listmap;

import org.junit.Test;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * LinkedBlockingQueue和ArrayBlockingQueue都是可阻塞的队列

 　内部都是使用ReentrantLock和Condition来保证生产和消费的同步；

 　当队列为空，消费者线程被阻塞；当队列装满，生产者线程被阻塞；
 */
public class TestQuence {

    class Person{
        long id;
        String name;
        String pwd;
        public Person(){

        }
        public Person(long id,String name,String pwd){
            this.id = id;
            this.name = name;
            this.pwd = pwd;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", pwd='" + pwd + '\'' +
                    '}';
        }
    }

    @Test
    public void testArrayBlockingQueue() throws InterruptedException {
        ArrayBlockingQueue queue = new ArrayBlockingQueue(10,true);
        LinkedBlockingQueue queue2 = new LinkedBlockingQueue();
        SynchronousQueue queue1 = new SynchronousQueue<Person>();
//        queue1.put();queue1.add()
//        queue2.add();queue2.put();
        queue.add(new Person(22,"aaa","aaaaa"));
        queue.add(new Person(11,"eeee","eeeee"));
        queue.add(new Person(56,"bbbb","bbbbbbbb"));

       Person p;
        while ((p=(Person)queue.take())!=null){
            System.out.println(p);
            p = null;
        }
    }

}
