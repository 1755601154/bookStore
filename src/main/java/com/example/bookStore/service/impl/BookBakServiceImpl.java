package com.example.bookStore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bookStore.mapper.BookBakMapper;
import com.example.bookStore.entity.BookBak;
import com.example.bookStore.service.BookBakService;
import org.springframework.stereotype.Service;

/**
 * (BookBak)表服务实现类
 *
 * @author yuanlei
 * @since 2021-04-26 23:21:25
 */
@Service("bookBakService")
public class BookBakServiceImpl extends ServiceImpl<BookBakMapper, BookBak> implements BookBakService {

}