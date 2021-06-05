package com.example.bookStore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bookStore.database.annotation.DataSource;
import com.example.bookStore.mapper.PricingRefValueMapper;
import com.example.bookStore.entity.PricingRefValue;
import com.example.bookStore.service.PricingRefValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (PricingRefValue)表服务实现类
 *
 * @author yuanlei
 * @since 2020-11-24 20:14:46
 */
@Service("pricingRefValueService")
@DataSource("SLAVE")
public class PricingRefValueServiceImpl extends ServiceImpl<PricingRefValueMapper, PricingRefValue> implements PricingRefValueService {

    @Autowired
    PricingRefValueMapper pricingRefValueMapper;
    /**
     * @param pricingRefValue
     * @return int
     * @Author yuanlei
     * @Description //
     * @Date 11:56 2020/11/27
     * @Param [pricingRefValue]
     */
    @Override
    public int updateByRefValueId(PricingRefValue pricingRefValue) {
        LambdaQueryWrapper<PricingRefValue> updateWrapper = new LambdaQueryWrapper();
        return pricingRefValueMapper.update(pricingRefValue,updateWrapper.eq(PricingRefValue::getRefValueId,pricingRefValue.getRefValueId()));

    }
}