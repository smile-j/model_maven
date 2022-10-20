package com.dong.base.test.io.filelock;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;

public class FilesDemo {

    public static void main(String[] args) throws Exception {
//        Path path = Paths.get("d://test");
//        Path directories = Files.createDirectories(path);//创建路径
//        System.out.println(directories);

        //文件复制
//        Path sourcePath = Paths.get("D:\\文档\\111.xlsx");
//        Path  newPath= Paths.get("d://test//f.xlsx");
//        Path copy = Files.copy(sourcePath, newPath);
//        System.out.println(copy);
        //文件移动
        Path sourcePath = Paths.get("D:\\文档\\111.xlsx");
        Path  newPath= Paths.get("d://test//f.xlsx");
        Path copy = Files.move(sourcePath, newPath);
        System.out.println(copy);
        //文件删除
        Files.delete(Paths.get(""));
        //递归便利
//        Files.walkFileTree(Paths.get(""),new SimpleFileVisitor<>());

    }

}
