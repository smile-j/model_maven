package com.dong.base.test.collection;

import org.junit.Test;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Administrator on 2017/12/7.
 * concurrent  英 [kənˈkʌrənt]  美 [kənˈkɜ:rənt]   同时发生的; 同时完成的; 同时存在的;
 *
 */
public class TestCollection {

    @Test
    public void testHashMap(){
        Person person = new Person(123,"zhang");
        DIYHashMap<String,Person>  hashMap = new DIYHashMap<String, Person>(16,0.75);
        hashMap.put("1",new Person(1,"aa"));
        hashMap.put("2",new Person(2,"bb"));
        hashMap.put("3",new Person(3,"cc"));
        hashMap.put("4",new Person(4,"dd"));
        System.out.println(hashMap.get("4")+"    "+hashMap.getClass());

        HashMap hashMap1 = new HashMap();
        hashMap1.put("1",new Person(1,"aa"));
        hashMap1.put("2",new Person(2,"bb"));
        hashMap1.put("3",new Person(3,"cc"));
        hashMap1.put("4",new Person(4,"dd"));
        System.out.println(hashMap1.getClass());


    }

    @Test
    public void test2(){
        WeakReference<String> sr = new WeakReference<String>(new String("hello"));
//            ReferenceQueue<String> sr = new ReferenceQueue<String>();



        System.out.println(sr.get());
        System.gc();                //通知JVM的gc进行垃圾回收
        System.out.println(sr.get());
    }

    @Test
    public void testSort(){
        System.out.println("start 。。。");
        Person person = new Person();
        System.out.println("................................");
        Person person2 = new Person();
        System.out.println("end 。。。");
    }
    @Test
    public void testConcurrent(){
        ConcurrentMap<String,String> concurrentMap = new ConcurrentHashMap();
        concurrentMap.put("ab","abc");
        String ab = concurrentMap.get("ab");
        System.out.println(ab);

    }

    @Test
    public void testSet(){
        //        List list = new ArrayList();
        TreeSet<Person> list = new TreeSet<Person>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return 0;
            }
        });
        list.add(new Person(11,"aa"));
//        list.add(null);
        list.add(new Person(11,"dd"));
        list.add(new Person(22,"bb"));
//        list.add("aaa");

        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println("entry:" + iterator.next());
        }
//        System.out.println("list....................end");
        HashMap<String,Person> map = new HashMap<String,Person>();
        map.put("aa",new Person(1,"aa"));
        map.put("cc",new Person(33,"cc"));
        map.put("bb",new Person(2,"bb"));
//        for(Map.Entry<String,Person> entry:map.entrySet()){
//            System.out.println(entry.getKey()+" ----- "+entry.getValue());
//        }
    }

    @Test
    public void testMap(){
//        Map<String,Person> map = new HashMap<String,Person>();
        TreeMap<String,Person> map = new TreeMap<String,Person>();
        map.put(null,null);
        for(int i = 0;i<100;i++){
            map.put("ffff"+i,new Person(10+i,"aa"+i));
        }
        map.put(null,new Person(11111,"111111"));
        Iterator it = map.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry  entry = (Map.Entry) it.next();
            System.out.println(entry.getKey()+"-----"+entry.getValue());
        }
    }

    @Test
    public void testLinkeList(){

        LinkedList<String> list = new LinkedList<>();
        list.add("cc");
        list.add("aa");
        list.add("dd");
        list.add("bb");



        LinkedList<String> list2 = new LinkedList<>();
        list2.add("111");
        list2.add("33");
        list.addAll(list2);
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }

    @Test
    public void testHashSet(){
        HashSet set = new HashSet();
        set.add(new Person(1,"aa"));
        set.add(new Person(2,"bb"));
        set.add(new Person(3,"cc"));
        set.add(new Person(1,"dd"));
        set.forEach(System.out::println);
    }

}
