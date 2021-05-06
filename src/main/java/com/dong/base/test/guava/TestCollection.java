package com.dong.base.test.guava;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Predicates;
import com.google.common.base.Splitter;
import com.google.common.collect.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestCollection {

    public static void main(String[] args) {


        // 普通Collection的创建
        List<String> list = Lists.newArrayList();
        Set<String> set = Sets.newHashSet();
        Map<String, String> map = Maps.newHashMap();
        // 不变Collection的创建
        ImmutableList<String> iList = ImmutableList.of("a", "b", "c");
        ImmutableSet<String> iSet = ImmutableSet.of("e1", "e2");
        ImmutableMap<String, String> iMap = ImmutableMap.of("k1", "v1", "k2", "v2");

        List<String> list1 = new ArrayList<String>();
        list1.add("aa");
        list1.add("bb");
        list1.add("cc");
        String result = Joiner.on("-").join(list1);
        //result为 aa-bb-cc

        Map<String, Integer> map2 = Maps.newHashMap();
        map2.put("xiaoming", 12);
        map2.put("xiaohong",13);
         result = Joiner.on(",").withKeyValueSeparator("=").join(map2);
        // result为 xiaoming=12,xiaohong=13

        //use guava
        String str = "1-2-3-4-5-6";
        list = Splitter.on("-").splitToList(str);
        Splitter.on("-").omitEmptyStrings().trimResults().splitToList(str);
        //list为 [1, 2, 3, 4, 5, 6]
    }

    public void test(){
        //按照条件过滤
        ImmutableList<String> names = ImmutableList.of("begin", "code", "Guava", "Java");
        Iterable<String> fitered = Iterables.filter(names, Predicates.or(Predicates.equalTo("Guava"), Predicates.equalTo("Java")));
        System.out.println(fitered); // [Guava, Java]
//自定义过滤条件 使用自定义回调方法对Map的每个Value进行操作
        ImmutableMap<String, Integer> m = ImmutableMap.of("begin", 12, "code", 15);
// Function<F, T> F表示apply()方法input的类型，T表示apply()方法返回类型
        Map<String, Integer> m2 = Maps.transformValues(m, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer input) {
                if(input>12){
                    return input;
                }else{
                    return input+1;
                }
            }
        });
        System.out.println(m2); //{begin=13, code=15}
    }
}
