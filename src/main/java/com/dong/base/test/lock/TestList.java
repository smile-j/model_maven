package com.dong.base.test.lock;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class TestList {

    public static void main(String[] args) {
        testArrayList();
    }

    /**
     *
     * 将指定元素添加到此列表的结尾：add(E e); || offer(E e);
     * 头插入   push
     * 获取并移除此列表的头：poll(); || remove();
     *
     */
    public static void testArrayList(){
        LinkedList<String> list = new LinkedList<>();
        for(int i=0;i<5;i++){
//            list.add(String.valueOf(i));
//            list.offer(String.valueOf(i));
            list.push(String.valueOf(i));
        }

       /* Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }*/

        while (list.size()>0){
            System.out.println(list.remove());
//            System.out.println(list.poll());
        }
        System.out.println("............end");
    }

}
