package com.example.bookStore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bookStore.database.annotation.DataSource;
import com.example.bookStore.mapper.BookMapper;
import com.example.bookStore.entity.Book;
import com.example.bookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yuanlei
 * @date 2020-11-18
 */
@Service
@DataSource
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Autowired
    BookMapper bookMapper;
    /**
     * @return java.util.List<com.example.bookStore.entity.Book>
     * @Author yuanlei
     * @Description //
     * @Date 21:59 2020/11/19
     * @Param []
     **/
    @Override
    public List<Book> getAllBooks() {
        return this.list();
    }

    /**
     * @param start
     * @param limit
     * @return java.util.List<com.example.bookStore.entity.Book>
     * @Author yuanlei
     * @Description //分页查询
     * @Date 19:46 2020/11/22
     * @Param [start, limit]
     */
    @Override
    @Transactional
    public List<Book> queryBookByLimit(int start, int limit) {
        return bookMapper.queryBookByLimit(start,limit);
    }
}
