package com.example.bookStore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.bookStore.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yuanlei
 * @date 2020-11-18
 */
@Mapper
@Repository
public interface BookMapper extends BaseMapper<Book> {
    /**
     * @Author yuanlei
     * @Description //分页查询
     * @Date 19:46 2020/11/22
     * @Param [start, limit]
     * @return java.util.List<com.example.bookStore.entity.Book>
     **/
    @Select("select * from book limit #{start},#{limit};")
    List<Book> queryBookByLimit(@Param("start") int start, @Param("limit") int limit);
}
