package com.dong.base.knowledge;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestReg {

    @Test
    public void test1(){

        String bufferStr = "sdfasfd手术sadfa ";
        //1. 是否包含
            // 1）
        if(bufferStr.matches(".*手术.*")||bufferStr.matches(".*就诊*.")) {//就诊信息 手术  就诊
            System.out.println(bufferStr+"-------"+true);
        }
        System.out.println("--------------------------------------------------------------------------------");
            // 2）
        String content = "I am noob " +
                "from runoob.com.";

        String pattern = ".*runoob.*";
        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println("字符串中是否包含了 'runoob' 子字符串? " + isMatch);
        System.out.println("--------------------------------------------------------------------------------");
        //2.替换
        String sexStr = bufferStr.replaceAll("\\s*", "").replaceAll("\\:","");
        //3.提取指定的字符串

        // 按指定模式在字符串查找
        String line = "This order was placed for QT3000! OK?";
        String pattern2 = "(\\D*)(\\d+)(.*)";
//        String pattern2 = "(\\D{0,2})";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern2);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        if (m.find( )) {
            System.out.println("Found value: " + m.group(0) );
            System.out.println("Found value: " + m.group(1) );
            System.out.println("Found value: " + m.group(2) );
            System.out.println("Found value: " + m.group(3) );
        } else {
            System.out.println("NO MATCH");
        }

        System.out.println("--------------------------------------------------------------------------------");
        String ss ="18501033065";
        final String REGEX_MOBILE = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
        System.out.println(ss.matches("\\d{11}"));
        System.out.println(ss.matches(REGEX_MOBILE));
        System.out.println("1890000222".matches("^189.*$"));
    }

}
