package com.dong.base.test.io;

import java.io.*;

public class TestSerializable {

    /**
     * 序列化与反序列化
     *
     * 序列化：把对象转换为字节序列的过程称为对象的序列化。
     * 反序列化：把字节序列恢复为对象的过程称为对象的反序列化。
     * 序列化主要有两种用途：
     *      把对象的字节序列永久地保存到硬盘上，通常存放在一个文件中；（持久化对象）
     *      在网络上传送对象的字节序列。（网络传输对象）
     * 部分属性的序列化
     *      使用transient修饰符
     *      使用static修饰符
     *      默认方法writeObject和readObject
     *
     */
    public static void main(String[] args) throws Exception {
        serializeFlyPig();
        FlyPig flyPig = deserializeFlyPig();
        System.out.println(flyPig.toString());

    }

    /**
     * 序列化
     */
    private static void serializeFlyPig() throws IOException {
        FlyPig flyPig = new FlyPig();
        flyPig.setColor("black");
        flyPig.setName("naruto");
        flyPig.setCar("0000");
        System.out.println(flyPig.toString());
        // ObjectOutputStream 对象输出流，将 flyPig 对象存储到E盘的 flyPig.txt 文件中，完成对 flyPig 对象的序列化操作
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("d:/flyPig.txt")));
        oos.writeObject(flyPig);
        System.out.println("FlyPig 对象序列化成功！");
        oos.close();
    }

    /**
     * 反序列化
     */
    private static FlyPig deserializeFlyPig() throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("d:/flyPig.txt")));
        FlyPig person = (FlyPig) ois.readObject();
        System.out.println("FlyPig 对象反序列化成功！");
        return person;
    }

}
