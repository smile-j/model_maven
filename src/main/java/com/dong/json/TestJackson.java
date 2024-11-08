package com.dong.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.experimental.Accessors;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TestJackson {

    public static void main(String[] args) {
        User lili = new User().setId(1001)
                .setUserName("lili")
                .setAge(18)
                .setBirthDay(new Date());
        ArrayList<User> listUser = Lists.newArrayList(new User().setId(1001)
                .setUserName("lili")
                .setAge(18)
                .setBirthDay(new Date()), new User().setId(1002)
                .setUserName("lucy")
                .setAge(19)
                .setBirthDay(new Date()));

        ObjectMapper mapper = new ObjectMapper();
        try {
            //进行一下日期转换
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            mapper.setDateFormat(dateFormat);
            String userJson = mapper.writeValueAsString(lili);
            String usersJson = mapper.writeValueAsString(listUser);
            System.out.println(userJson);
            System.out.println(usersJson);
//            User user = mapper.convertValue(userJson, User.class);
            User user2 = mapper.readValue(userJson, User.class);
            ArrayList<User> users = mapper.readValue(usersJson, new TypeReference<ArrayList<User>>() {
            });
            System.out.println(users.size());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


    private static ObjectMapper mapper;
    public static ObjectMapper getObjectMapper(){
        return mapper;
    }
    static {
         //创建ObjectMapper对象
            mapper = new ObjectMapper();

            //configure方法 配置一些需要的参数
            // 转换为格式化的json 显示出来的格式美化
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            //序列化的时候序列对象的那些属性
            //JsonInclude.Include.NON_DEFAULT 属性为默认值不序列化
            //JsonInclude.Include.ALWAYS      所有属性
            //JsonInclude.Include.NON_EMPTY   属性为 空（“”） 或者为 NULL 都不序列化
            //JsonInclude.Include.NON_NULL    属性为NULL 不序列化
            mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);


            //反序列化时,遇到未知属性会不会报错
            //true - 遇到没有的属性就报错 false - 没有的属性不会管，不会报错
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            //如果是空对象的时候,不抛异常
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

            // 忽略 transient 修饰的属性
            mapper.configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, true);

            //修改序列化后日期格式
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

            //处理不同的时区偏移格式
//            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//            mapper.registerModule(new JavaTimeModule());

    }


        @Data
    @Accessors(chain = true)
    public static class User{

        private Integer id;

        private String userName;

        private Integer age ;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date birthDay;

    }

}
