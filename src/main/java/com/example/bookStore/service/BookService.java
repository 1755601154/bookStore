package com.example.bookStore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.bookStore.database.annotation.DataSource;
import com.example.bookStore.entity.Book;

import java.util.List;

/**
 * @author yuanlei
 * @date 2020-11-18
 */
@DataSource
public interface BookService extends IService<Book> {

    /**
     * @Author yuanlei
     * @Description //
     * @Date 21:59 2020/11/19
     * @Param []
     * @return java.util.List<com.example.bookStore.entity.Book>
     **/
    List<Book> getAllBooks();

    /**
     * @Author yuanlei
     * @Description //分页查询
     * @Date 19:46 2020/11/22
     * @Param [start, limit]
     * @return java.util.List<com.example.bookStore.entity.Book>
     **/
    List<Book> queryBookByLimit(int start, int limit);

    /**
     * @Author yuanlei
     * @Description //查询某行数据，并把这行加排他锁
     * @Date 10:34 2021/1/27
     * @Param [id]
     * @return com.example.bookStore.entity.Book
     **/
    Book getBookByIdForUpdate(Integer id);
}
