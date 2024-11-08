package com.dong.json;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestFastJson {

    public static void main(String[] args) {
        //对象互相转换
        User lili = new User().setId(1001)
                .setUserName("lili")
                .setAge(18)
                .setBirthDay(new Date());
        String userStr = JSONObject.toJSONString(lili);
        System.out.println(userStr);
        User tempUser = JSONObject.parseObject(userStr, User.class);
        System.out.println("=================================");
        //数组互相转换
        ArrayList<User> listUser = Lists.newArrayList(new User().setId(1001)
                .setUserName("lili")
                .setAge(18)
                .setBirthDay(new Date()), new User().setId(1002)
                .setUserName("lucy")
                .setAge(19)
                .setBirthDay(new Date()));
        String usersStr = JSONObject.toJSONString(listUser);
        System.out.println(usersStr);
        List<User> users = JSONObject.parseArray(usersStr, User.class);
        System.out.println(users.size());
    }

    @Data
    @Accessors(chain = true)
    public static class User{

        private Integer id;

        private String userName;

        private Integer age ;

        @JSONField(format = "yyyy-MM-dd HH:mm:ss")
        private Date birthDay;

    }


}
