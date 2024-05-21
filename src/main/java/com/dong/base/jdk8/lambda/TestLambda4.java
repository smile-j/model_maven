package com.dong.base.jdk8.lambda;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.zip.CheckedOutputStream;

public class TestLambda4 {
    ArrayList<Person> personList = Lists.newArrayList(new Person().setId(1).setName("aa").setAdd("北京,上海"), new Person().setId(2).setName("bb").setAdd("北京,广州"));


    /**
     * 复杂
     * groupby
     * map
     */
    @Test
    public void test2(){
        /**
         * Map<name,List<city>
         */
        Map<String, List<String>> collect = personList.stream().collect(Collectors.groupingBy(Person::getName
                , Collectors.mapping(Person::getName
                        , Collectors.filtering(StringUtils::isNotBlank
                                , Collectors.mapping(city -> city.split(",")
                                        , Collectors.flatMapping(Arrays::stream
                                                , Collectors.filtering(StringUtils::isNotBlank
                                                        , Collectors.toList())))))));
        System.out.println(JSONObject.toJSONString(collect));
    }

    /**
     * groupby
     * map
     */
    @Test
    public void test1(){
        personList.stream().collect(Collectors.groupingBy(Person::getName));
        personList.stream().collect(Collectors.groupingBy(Person::getName,Collectors.toList()));
        personList.stream().collect(Collectors.groupingBy(Person::getName,Collectors.mapping(Person::getId,Collectors.toList())));

        personList.stream().collect(Collectors.toMap(Person::getName, Function.identity(),(e1,e2)->e1));
        personList.stream().collect(Collectors.toMap(Person::getName,Person::getId,(e1,e2)->e1));
    }
}
