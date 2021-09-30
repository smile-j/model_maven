package com.dong.base.test.design.observerPattern.test2;

import java.io.IOException;
import java.util.Properties;

public class ObserveTest {

    /**
     * @param args
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static void main(String[] args) throws Exception {
        //读取配置文件的操作改成了静态方法，使用的时候直接调用，下面的observers是我们的配置文件的文件名
        String observers[] = PropertyMgr.getProperty("observers").split(",");
        Child child = new Child();
        for (String s : observers) {
            child.addWakenUpListener((WakeUpListener) Class.forName(s)
                    .newInstance());
        }
        new Thread(child).start();
    }
}

//这里将读取配置文件Observers.properties的操作封装在类中，使用静态代码块的形式，在类加载的时候将配置文件加载进内存
//提高代码的灵活行，避免反复的执行加载配置文件的操作
class PropertyMgr {
    // 重要的思想：缓存
    // 单例初步以及缓存:把硬盘上的内容缓存到内存上
    // 缓存的策略:访问最多的文件进行缓存
    private static Properties props = new Properties();
    // 这里使用了静态代码块，类加载的时候初始化一次
    static {

            try {
                props.load(ObserveTest.class.getClassLoader().getResourceAsStream(
                        "Observers.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
    //定义成静态static方法，方便在类外直接访问
    public static String getProperty(String key) throws IOException {
        return props.getProperty(key);

    }
}
