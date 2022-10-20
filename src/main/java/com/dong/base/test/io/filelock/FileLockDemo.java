package com.dong.base.test.io.filelock;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileLockDemo {
    public static void main(String[] args) throws Exception {

//        String input ="File lock hello";
        String input ="*******";

        ByteBuffer byteBuffer = ByteBuffer.wrap(input.getBytes());

        String  filePath = "d:\\1.txt";
        Path path = Paths.get(filePath);
        FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.WRITE,StandardOpenOption.APPEND);
        //设置写入的位置
        fileChannel.position(0);
//        fileChannel.position(fileChannel.size());
        //加锁
        FileLock lock = fileChannel.lock();
//        FileLock lock = fileChannel.lock(0L, Long.MAX_VALUE, true);
        System.out.println("是否共享锁："+lock.isShared());
        fileChannel.write(byteBuffer);
        /**
         * 文件锁属于jvm实例持有，一旦获取 要调用release() 或者关闭对应的FileChannel对象，或者当前jvm推出
         * 不能叠加使用
         */
        fileChannel.close();

        //读文件
        readFile(filePath);
    }

    private static void readFile(String filePaht) throws Exception {

        FileReader fileReader = new FileReader(filePaht);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String tr = bufferedReader.readLine();
        System.out.println("读取内容");
        while (tr!=null){
            System.out.println(" "+tr);
            tr= bufferedReader.readLine();
        }

        fileReader.close();
        bufferedReader.close();

    }

}
