package com.dong.example.permission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.junit.Test;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/12/8
 */
public class PermissionDemo2 {


    /*
        默认创建一个文件和两个用户，并且将他们的权限默认为全空
     */

    private File file = new File("系统文件.txt", 0B000);
    private User user1 = new User("用户一", 0B000);
    private User user2 = new User("用户二", 0B000);

    private AuthTools authTools = AuthTools.authTools;

    /**
     * 给user1赋予Read权限
     */
    @Test
    public void addUser1Read() {
        System.out.println("赋值前：" + user1);
        authTools.addUserAuth(user1, Authority.READABLE);
        System.out.println("赋值后：" + user1);

    }

    /**
     * 给user2赋予Write和Runnable权限
     */
    @Test
    public void addUser2Write() {
        System.out.println("赋值前：" + user2);
        authTools.addUserAuth(user2, Authority.WRITABLE, Authority.RUNNABLE);
        System.out.println("赋值后：" + user2);
    }

    /**
     * 删除use1的可运行权限
     */
    @Test
    public void delUser1Write() {
        // 先给予全部权限
        authTools.addUserAuth(user1, Authority.READABLE, Authority.WRITABLE, Authority.RUNNABLE);
        System.out.println("user1：" + user1);
        authTools.delUserAuth(user1, Authority.RUNNABLE);
        System.out.println("删除可运行权限后user1：" + user1);
    }

    /**
     * 测试file的运行的情况
     */
    @Test
    public void execFileByUser() {
        authTools.addFileAuth(file, Authority.RUNNABLE, Authority.READABLE);
        System.out.println("file：" + file);
        authTools.addUserAuth(user1, Authority.READABLE);
        System.out.println("user1："+user1);
        System.out.println("user2："+user2);
        authTools.execOpera(user1, file, Authority.READABLE);
        authTools.execOpera(user1, file, Authority.RUNNABLE);
        authTools.execOpera(user2, file, Authority.READABLE);
    }



    @AllArgsConstructor
    @Data
    public static class User {

        private String name;

        /**
         * 默认权限全空
         */
        private Integer auth = 0B000;

        @Override
        public String toString() {
            return name + "拥有" + AuthTools.authTools.pintf(this.auth);
        }
    }

    @AllArgsConstructor
    @Data
    public class File {

        private String name;

        /**
         * 默认权限全空
         */
        private Integer auth = 0B000;

        @Override
        public String toString() {
            return name+"拥有"+ AuthTools.authTools.pintf(this.auth);
        }
    }
    // 操作枚举
    @AllArgsConstructor
    @Getter
    public enum Authority {

        /**
         * 读权限 0B100 = 4
         */
        READABLE(0B100, "可读"),
        /**
         * 读权限 0B010 = 2
         */
        WRITABLE(0B010, "可写"),
        /**
         * 读权限 0B001 = 1
         */
        RUNNABLE(0B001, "可运行");

        private Integer value;
        private String name;
    }

}
