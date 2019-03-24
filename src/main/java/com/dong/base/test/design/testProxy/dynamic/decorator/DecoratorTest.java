package com.dong.base.test.design.testProxy.dynamic.decorator;


import com.dong.base.test.design.testProxy.dynamic.entity.Book;
import com.dong.base.test.design.testProxy.dynamic.jdkproxy.BookFacade;

/**
 * Created by Administrator on 2018/1/5.
 */
public class DecoratorTest implements BookFacade {

    private BookFacade bookFacade;

    public DecoratorTest(BookFacade bookFacade){
        bookFacade = bookFacade;
    }

    @Override
    public void addBook(Book book) {
        bookFacade.addBook(book);
    }

    @Override
    public boolean updateBook(Book book) {
        return bookFacade.updateBook(book);
    }

    @Override
    public String deleteBook(String id) {
        return bookFacade.deleteBook(id);
    }
}
