package com.dong.base.test.baseTest;

import com.dong.base.test.entity.TransientEntity;
import com.dong.base.test.entity.TransientEntity2;
import org.junit.Test;

import java.io.*;

/**
 * Created by Administrator on 2018/1/23.
 */
public class KeyWordTest {

    @Test
    public void testTransient(){
        TransientEntity entity = new TransientEntity(11,"aaaaa","aaaaaa");
        System.out.println("entity:"+entity);
        System.out.println("username: " + entity.getName());
        System.err.println("password: " + entity.getPwd());
        System.err.println("id: " + entity.getId());

        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("C:/user.txt"));
            os.writeObject(entity); // 将User对象写进文件
            os.flush();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("***************************************************************");

        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(
                    "C:/user.txt"));
            entity = (TransientEntity) is.readObject(); // 从流中读取User的数据
            is.close();

            System.out.println("\nread after Serializable: ");
            System.out.println("username: " + entity.getName());
            System.err.println("password: " + entity.getPwd());
            System.err.println("id: " + entity.getId());
            System.out.println("entity:"+entity);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testTransient2(){
        TransientEntity2 entity = new TransientEntity2(222,"aaaaa","aaaaaa");
        System.out.println("entity:"+entity);
        System.out.println("username: " + entity.getName());
        System.err.println("password: " + entity.getPwd());
        System.err.println("id: " + entity.getId());

        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("C:/user.txt"));
            os.writeObject(entity); // 将User对象写进文件
            os.flush();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("***************************************************************");

        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(
                    "C:/user.txt"));
            entity = (TransientEntity2) is.readObject(); // 从流中读取User的数据
            is.close();

            System.out.println("\nread after Serializable: ");
//            System.out.println("username: " + entity.getName());
//            System.err.println("password: " + entity.getPwd());
//            System.err.println("id: " + entity.getId());
            System.out.println("entity:"+entity);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
