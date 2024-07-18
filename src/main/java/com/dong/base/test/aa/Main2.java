package com.dong.base.test.aa;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main2 {


    public static void main(String[] args){

        List<TestUser> users = Lists.newArrayList(
                new TestUser(1,"bj","北京","ab",1,1),
                new TestUser(2,"bj","北京","ab1",2,1),
                new TestUser(2,"bj","北京","ab2",3,1),
                new TestUser(3,"bj","北京","ad",4,1),
                new TestUser(4,"bj","北京","ad1",5,1),
                new TestUser(4,"bj","北京","ad2",6,1),

                new TestUser(5,"sh","上海","ab",7,1),
                new TestUser(6,"sh","上海","ab",8,1),
                new TestUser(6,"sh","上海","ad",9,1),
                new TestUser(6,"sh","上海","ad",10,1)
        );
        Set<String> key = Sets.newHashSet("ab","ad");
        HashMap<String,List<String>> kk = Maps.newHashMap();
        kk.put("ab",Lists.newArrayList("ab1","ab2"));
        kk.put("ad",Lists.newArrayList("ad1","ad2"));
        //北京，上海的值
        List<TestUser> collect1 = users.stream().filter(e -> key.contains(e.getAdId())).collect(Collectors.groupingBy(TestUser::getRegionCode)).entrySet().stream().map(e -> {
            TestUser user = new TestUser(0,e.getKey(),"","",Integer.valueOf(String.valueOf(e.getValue().stream().collect(Collectors.summarizingInt(TestUser::getValue)).getSum())),
            Integer.valueOf(String.valueOf(e.getValue().stream().collect(Collectors.summarizingInt(TestUser::getValue2)).getSum())));
            return user;
        }).collect(Collectors.toList());
        System.out.println("1-->"+JSONObject.toJSONString(collect1));
        //北京 b1,ab2，上海的值ad1 ad2

        //ab ab1,ab2;ad ad1 ad2
    }


    public static void maina(String[] args) {

        int a[] =new int[]{1,2,6,9,9,1,2};
        int j =0;
        for (int i=0;i<a.length;i++) {
            j=j^a[i];
        }
        System.out.println(j);

    }







    public static void mainqq(String[] args) {
        String p ="[A-Za-z]*";
        String n ="[0-9]*";
        System.out.println(Pattern.matches(p,"123"));
        System.out.println(Pattern.matches(n,"123"));
        System.out.println(Pattern.matches(p,"ab"));
        System.out.println(Pattern.matches(n,"ab"));
        System.out.println(Pattern.matches(p,"ab1"));
        System.out.println(Pattern.matches(n,"ab2"));
    }

}
