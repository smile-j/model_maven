package com.dong.base.test.concurrent;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2018/2/5.
 */
public class LocalCache {

    private LocalCache() {
    }   // 防止在外部实例化

    // 使用volatile延迟初始化，防止编译器重排序
    private static volatile LocalCache instance;

    public static LocalCache getInstance() {

        // 对象实例化时与否判断（不使用同步代码块，instance不等于null时，直接返回对象，提高运行效率）
        if (instance == null) {
            // 同步代码块（对象未初始化时，使用同步代码块，保证多线程访问时对象在第一次创建后，不再重复被创建）
            synchronized (LocalCache.class) {
                // 未初始化，则初始instance变量
                if (instance == null) {
                    instance = new LocalCache();
                }
            }
        }
        return instance;
    }

    protected static final ConcurrentHashMap<String, Object> dataMap = new ConcurrentHashMap<String, Object>();

    private static final Object lock = new Object();

    public static Object get(String key) {

        Object v = dataMap.get(key);
        if (v == null) {
            synchronized (lock) {

                v = dataMap.get(key);     // Check again to avoid re-load
            }
        }
        return v;
    }

    public static synchronized void set(String key, Object value) {

        dataMap.put(key, value);
    }

    public static synchronized boolean isAccess(String ip, long maxTimeInterval, long maxTimes) {
        @SuppressWarnings("unchecked")
        HashMap<String, Object> map = (HashMap<String, Object>) dataMap.get(ip);
        if (null == map) {
            map = new HashMap<String, Object>();
            map.put("times", 1);
            map.put("timestamp", System.currentTimeMillis());
            LocalCache.set(ip, map);
            System.err.println(ip+":"+map);
            return true;
        } else {
            int times = Integer.parseInt(map.get("times").toString());
            long timestamp =  Long.parseLong(map.get("timestamp").toString());
            if (System.currentTimeMillis() - timestamp > maxTimeInterval*1000) {
                map.put("times", 1);
                map.put("timestamp", System.currentTimeMillis());
                LocalCache.set(ip, map);
                System.err.println(ip+":"+map);
                return true;
            } else {
                if (times < maxTimes) {
                    map.put("times", times + 1);
                    LocalCache.set(ip, map);
                    System.err.println(ip+":"+map);
                    return true;
                } else {
                    System.err.println("false");
                    return false;
                }
            }
        }
    }

}
