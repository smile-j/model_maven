package com.dong.base.test.design.testProxy.dynamic.jdkproxy;


import com.dong.base.test.design.testProxy.dynamic.entity.Book;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/4.
 */
public class BookFacadeImpl implements BookFacade {

    private static Map<String,Book> books = new HashMap();

    public static Map<String,Book> getBooks(){
        return books;
    }

    public BookFacadeImpl() {
        books.put("001",new Book("001","西游记","四大名著"));
        books.put("002",new Book("001","鲁滨孙漂流记","外国小说"));
        books.put("003",new Book("001","西游记","现代小说"));
    }

    @Override
    public void addBook(Book book) {
        books.put(book.getId(),book);
        System.out.println("增加图书方法。。。");
    }

    @Override
    public boolean updateBook(Book book) {
        System.out.println("更新图书方法。。。");
        if(books.containsKey(book.getId())){
            books.put(book.getId(),book);
            return true;
        }
        return false;
    }

    @Override
    public String deleteBook(String id) {
        if("001".equals(id)){
           throw  new RuntimeException(id+"不能被删除！！！");
        }
        books.remove(id);
        System.out.println("删除图书方法。。。");
        return "删除图书方法";
    }
}
