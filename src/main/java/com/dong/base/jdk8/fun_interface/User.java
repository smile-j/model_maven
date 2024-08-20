package com.dong.base.jdk8.fun_interface;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@AllArgsConstructor
@Accessors(chain = true)
public class User {

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 重写equals和hashCode
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User user = (User) obj;
            if (name.equals(user.name)){
                return true;
            }
        }
        return false;
    }
    @Override
    public int hashCode () {
        return name.hashCode();
    }



}
