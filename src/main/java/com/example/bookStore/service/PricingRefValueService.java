package com.example.bookStore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.bookStore.entity.PricingRefValue;

/**
 * (PricingRefValue)表服务接口
 *
 * @author yuanlei
 * @since 2020-11-24 20:10:28
 */
public interface PricingRefValueService extends IService<PricingRefValue> {

    /**
     * @Author yuanlei
     * @Description //
     * @Date 11:56 2020/11/27
     * @Param [pricingRefValue]
     * @return int
     **/
    public int updateByRefValueId(PricingRefValue pricingRefValue);
}