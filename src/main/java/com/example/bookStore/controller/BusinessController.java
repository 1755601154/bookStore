package com.example.bookStore.controller;

import com.example.bookStore.entity.Book;
import com.example.bookStore.entity.PricingRefValue;
import com.example.bookStore.service.BookService;
import com.example.bookStore.service.PricingRefValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yuanlei
 * @description
 * @date 2020-11-27
 */
@RestController
@RequestMapping(value="/business")
public class BusinessController {
    @Autowired
    BookService bookService;

    @Resource
    private PricingRefValueService pricingRefValueService;

    @RequestMapping(value="/testMultipleDataSourceTransactional",method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public String testMultipleDataSourceTransactional(){
        PricingRefValue pricingRefValue = new PricingRefValue();
        pricingRefValue.setRefValueName("固定费用(测试)");
        pricingRefValue.setRefValueId(93000500);
        pricingRefValueService.updateByRefValueId(pricingRefValue);
        //System.out.println(100/0);
        Book book = new Book();
        book.setTitle("测试多数据源事务");
        book.setId(100);
        bookService.updateById(book);
        //System.out.println(100/0);
        pricingRefValue.setRefValueName("产品账期有效天数(测试)");
        pricingRefValue.setRefValueId(93000501);
        pricingRefValueService.updateByRefValueId(pricingRefValue);
        return "ok";
    }
}
