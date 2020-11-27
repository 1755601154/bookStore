package com.example.bookStore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.example.bookStore.entity.PricingRefValue;
import org.springframework.stereotype.Repository;

/**
 * (PricingRefValue)表数据库访问层
 *
 * @author yuanlei
 * @since 2020-11-24 20:07:18
 */
@Mapper
@Repository
public interface PricingRefValueMapper extends BaseMapper<PricingRefValue> {

}