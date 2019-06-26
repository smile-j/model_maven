package com.dong.base.lock;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderUtil {

    static int num =0;
    public static String getOrderNum(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(new Date())+(++num);
    }

}
