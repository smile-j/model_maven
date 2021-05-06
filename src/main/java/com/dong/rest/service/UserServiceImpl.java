package com.dong.rest.service;


import com.dong.example.User;

import java.util.Optional;

/**
 * Created by Administrator on 2018/3/1.
 */
//@Path("users")
//@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
//@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class UserServiceImpl {

    public static void main(String[] args) {

        User user = new User();
        Optional<User> emptyOpt = Optional.ofNullable(null);

//        System.out.println(emptyOpt.get());
        System.out.println(emptyOpt.isPresent());

    }

}
