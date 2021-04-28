package com.example.bookStore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.example.bookStore.entity.BookBak;
import org.springframework.stereotype.Repository;

/**
 * (BookBak)表数据库访问层
 *
 * @author yuanlei
 * @since 2021-04-26 23:21:25
 */
@Mapper
@Repository
public interface BookBakMapper extends BaseMapper<BookBak> {

}