package com.dong.base.test.design.testProxy.dynamic.cglib;



import com.dong.base.test.design.testProxy.dynamic.entity.Book;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/4.
 * 使用cglib动态代理
 */
public  class BookFacadeImpl1  {

    private static Map<String,Book> books = new HashMap();

    public static Map<String,Book> getBooks(){
        return books;
    }

    public BookFacadeImpl1() {
        System.out.println("构造方法...................");
        books.put("001",new Book("001","西游记","四大名著"));
        books.put("002",new Book("001","鲁滨孙漂流记","外国小说"));
        books.put("003",new Book("001","西游记","现代小说"));
    }

    public void addBook() {
        System.out.println("增加图书的普通方法...");
    }

    public final boolean updateBook(Book book) {
        System.out.println("更新图书方法。。。");
        if(books.containsKey(book.getId())){
            books.put(book.getId(),book);
            return true;
        }
        return false;
    }

    public  boolean updateBook2(Book book) {
        System.out.println("更新图书方法。。。");
        if(books.containsKey(book.getId())){
            books.put(book.getId(),book);
            return true;
        }
        return false;
    }
}
