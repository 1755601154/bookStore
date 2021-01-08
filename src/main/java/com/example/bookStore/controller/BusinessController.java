package com.example.bookStore.controller;

import com.example.bookStore.datasource.DynamicDataSourceContextHolder;
import com.example.bookStore.entity.Book;
import com.example.bookStore.entity.BusClick;
import com.example.bookStore.entity.PricingRefValue;
import com.example.bookStore.service.BookService;
import com.example.bookStore.service.PricingRefValueService;
import com.example.bookStore.util.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        DynamicDataSourceContextHolder.setDataSourceType("SLAVE");
        PricingRefValue pricingRefValue = new PricingRefValue();
        pricingRefValue.setRefValueName("固定费用(测试)");
        pricingRefValue.setRefValueId(93000500);
        pricingRefValueService.updateByRefValueId(pricingRefValue);
        //System.out.println(100/0);
        Book book = new Book();
        book.setTitle("测试多数据源事务");
        book.setId(100);
        DynamicDataSourceContextHolder.setDataSourceType("MASTER");
        bookService.updateById(book);
        //System.out.println(100/0);
        pricingRefValue.setRefValueName("产品账期有效天数(测试)");
        pricingRefValue.setRefValueId(93000501);
        DynamicDataSourceContextHolder.setDataSourceType("SLAVE");
        pricingRefValueService.updateByRefValueId(pricingRefValue);
        return "ok";
    }
}
