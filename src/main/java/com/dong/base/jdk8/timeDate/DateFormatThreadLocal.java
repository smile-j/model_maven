package com.dong.base.jdk8.timeDate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/11/19.
 */
public class DateFormatThreadLocal {

    private static final ThreadLocal<DateFormat> df  = new ThreadLocal<DateFormat>(){
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd");
        }
    };

    public static Date convert(String source) throws ParseException {
        return df.get().parse(source);
    }


}
