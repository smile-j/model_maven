package com.dong.base.test.listmap;

import com.dong.base.test.design.decorator.first.Person;
import org.junit.Test;

import java.util.*;

/**
 * Created by Administrator on 2018/1/10.
 */
public class TestList {

    @Test
    public void test1(){
        /**
         * LinkedList使用了循环双向链表数据结构
         *
         * ArrayList是实现了基于动态数组的数据结构，LinkedList基于链表的数据结构。
         * 对于随机访问get和set，ArrayList觉得优于LinkedList，因为LinkedList要移动指针。ArrayList查找 O(1)  LinkedList查找 O(n)
         * 对于新增和删除操作add和remove，LinedList比较占优势，因为ArrayList要移动数据。
         */
        List<Integer> list2 = new LinkedList();//List  有序  可重复
        list2.add(12);
        list2.add(10);
        list2.add(20);
        list2.add(8);
        list2.add(30);
        list2.add(null);
        list2.add(null);
        System.out.println(list2.toString());

        Set<Integer> set = new HashSet();//无序   不可重复
        set.add(20);
        set.add(10);
        set.add(30);
        set.add(50);
        set.add(2);
        set.add(null);
        set.add(null);
        System.out.println(set.toString());


    }

    @Test
    public void testSort(){
        List<Person> list = new ArrayList();
        list.add(new Person(12,"aaa"));
        list.add(new Person(10,"bbb"));
        list.add(new Person(20,"ccc"));
        list.add(new Person(30,"ddd"));
        list.add(new Person(2,"eee"));
        System.out.println(list);
        Collections.sort(list, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge()-o2.getAge();
            }
        });

        System.out.println(list);
    }

}
