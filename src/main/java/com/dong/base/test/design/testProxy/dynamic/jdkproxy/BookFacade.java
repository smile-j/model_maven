package com.dong.base.test.design.testProxy.dynamic.jdkproxy;


import com.dong.base.test.design.testProxy.dynamic.entity.Book;

/**
 * Created by Administrator on 2018/1/4.
 */
public interface BookFacade {
    public void addBook(Book book);
    public boolean updateBook(Book book);
    public String deleteBook(String id);

}
