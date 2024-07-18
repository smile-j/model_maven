package com.dong.base.test.aa;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<UserVO> list = Lists.newArrayList(new UserVO("aa",3,5)
                ,new UserVO("bb",1,2),
                new UserVO("bb",2,3));
        Map<String, List<UserVO>> collect = list.stream().collect(Collectors.groupingBy(UserVO::getName));
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserVO{
        private String name;
        private Integer a1;
        private Integer a2;
    }
}
