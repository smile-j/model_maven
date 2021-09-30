package com.dong.base.util;

import com.alibaba.fastjson.JSON;
import com.dong.base.model.UserEntity;
import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.List;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/9/24
 */
public class Main {

    public static void main(String[] args) {
        List<UserEntity> users = Lists.newArrayList(
                UserEntity.builder().nickName("aa").id(5).loginCounts(1).build(),
                UserEntity.builder().nickName("bb").id(9).loginCounts(9).build(),
                UserEntity.builder().nickName("cc").id(3).loginCounts(11).build(),
                UserEntity.builder().nickName("dd").id(11).loginCounts(22).build(),
                UserEntity.builder().nickName("ee").id(12).loginCounts(21).build(),
                UserEntity.builder().nickName("ff").id(12).loginCounts(11).build(),
                UserEntity.builder().nickName("gg").id(12).loginCounts(2).build()
        );
        users.sort(new Comparator<UserEntity>() {
            @Override
            public int compare(UserEntity o1, UserEntity o2) {
                int ii = o1.getId().compareTo(o2.getId());
                if (ii==0){
                    ii = o1.getLoginCounts().compareTo(o2.getLoginCounts());
                }
                return ii;
            }
        });
        System.out.println(JSON.toJSON(users));
    }



}
