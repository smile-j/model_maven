package com.dong.example.permission;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/12/8
 */
public class AuthTools {

    public static AuthTools authTools = new AuthTools();

    /**
     * Description:
     * 给user 添加 authoritys 的权限
     *
     * @param user:
     * @param authoritys:
     * @date 2020/12/13 10:11
     **/
    public void addUserAuth(PermissionDemo2.User user, PermissionDemo2.Authority... authoritys) {
        user.setAuth(this.addAuth(user.getAuth(), authoritys));
    }

    /**
     * Description:
     * 给user 删除 authoritys 的权限
     *
     * @param user:
     * @param authoritys:
     * @date 2020/12/13 10:11
     **/
    public void delUserAuth(PermissionDemo2.User user, PermissionDemo2.Authority... authoritys) {
        user.setAuth(this.delAuth(user.getAuth(), authoritys));
    }

    /**
     * Description:
     * 给file 添加 authoritys 的权限
     *
     * @param file:
     * @param authoritys:
     * @date 2020/12/13 10:11
     **/
    public void addFileAuth(PermissionDemo2.File file, PermissionDemo2.Authority... authoritys) {
        file.setAuth(this.addAuth(file.getAuth(), authoritys));
    }

    /**
     * Description:
     * 给file 删除 authoritys 的权限
     *
     * @param file:
     * @param authoritys:
     * @date 2020/12/13 10:11
     **/
    public void delFileAuth(PermissionDemo2.File file, PermissionDemo2.Authority... authoritys) {
        file.setAuth(this.delAuth(file.getAuth(), authoritys));
    }

    /**
     * Description:
     * user对file执行operation操作
     *
     * @param user:
     * @param file:
     * @param operation:
     * @date 2020/12/13 10:11
     **/
    public void execOpera(PermissionDemo2.User user, PermissionDemo2.File file, PermissionDemo2.Authority operation) {
        // 执行操作主程序

        // 执行判断逻辑，只有user和file都有operation的权限的时候才可以执行
        // 比如 user:0B101 file:0B110 operation:0B100 就可以
        // 比如 user:0B001 file:0B110 operation:0B100 不可以 user用户本身就没有读权限
        // 细粒度分析：只要对应位置的二进制都是1就为true,否则为false
        if (this.judgeOperation(user.getAuth(), operation) &&
                this.judgeOperation(file.getAuth(), operation)) {
            System.out.println(user.getName() + "进行" + operation.getName() + "操作成功");
        } else {
            System.out.println(user.getName() + "进行" + operation.getName() + "操作失败");
        }

    }

    private Integer addAuth(Integer integer, PermissionDemo2.Authority... authorities) {
        // 添加权限主程序

        // 获取本次操作的实际权限集合
        Integer mergeAuth = mergeAuth(authorities);
        // 返回二者的合并即可
        return integer | mergeAuth;
    }

    private Integer delAuth(Integer integer, PermissionDemo2.Authority... authorities) {
        // 删除权限主程序

        // 获取本次操作的实际权限集合
        Integer mergeAuth = mergeAuth(authorities);

        // 返回二者的差
        // 例子：user的权限0B110（读写权限）删除0B011(执行与写权限)，结果为0B100(读权限)
        // 细粒度分析：0+0= 0，1+0 = 1， 0+1=0，1+1=0 （+代表位运算符号可能是| 也可能是 & 也可能是组合的）
        integer = ~(~integer | mergeAuth);
        return integer;
    }

    /**
     * Description:
     * 用于计算出本次操作总权限是什么
     *
     * @param authorities: 权限集合
     * @return java.lang.Integer: 合并总权限
     * @date 2020/12/13 10:27
     **/
    private Integer mergeAuth(PermissionDemo2.Authority... authorities) {
        Integer auth = 0B000;
        for (PermissionDemo2.Authority authority : authorities) {
            // 二进制位与操作
            // 解析：此处是合并操作，说明是要从 0 -> 1的变化
            // 例子：0B000(空权限) + 0B100(读权限) = 0B100(读权限)
            // 位运算细粒度分析： 0+1=1、0+0=0、1+0=1，1+1=1。（+代表位运算符号可能是| 也可能是 & 也可能是组合的）
            auth = auth | authority.getValue();
        }
        return auth;
    }

    private Boolean judgeOperation(Integer auth, PermissionDemo2.Authority authority) {
        // 细粒度分析 0+0=0, 0+1=0, 1+0=0, 1+1=1
        return (auth & authority.getValue()) == authority.getValue();
    }

    /**
     * Description:
     * 打印interger所拥有的权限
     *
     * @param integer:
     * @return java.lang.String
     * @date 2020/12/13 11:20
     **/
    public String pintf(Integer integer) {
        String str = "【";
        if (this.judgeOperation(integer, PermissionDemo2.Authority.READABLE)) {
            str += PermissionDemo2.Authority.READABLE.getName();
        }
        if (this.judgeOperation(integer, PermissionDemo2.Authority.WRITABLE)) {
            str += "、" + PermissionDemo2.Authority.WRITABLE.getName();
        }
        if (this.judgeOperation(integer, PermissionDemo2.Authority.RUNNABLE)) {
            str += "、" + PermissionDemo2.Authority.RUNNABLE.getName();
        }
        return str + "】";
    }

}
