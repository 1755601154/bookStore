package com.example.bookStore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.bookStore.entity.Book;

import java.util.List;

/**
 * @author yuanlei
 * @date 2020-11-18
 */
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
}
