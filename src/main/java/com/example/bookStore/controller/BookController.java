package com.example.bookStore.controller;

import com.example.bookStore.annotation.MethodTiming;
import com.example.bookStore.entity.Book;
import com.example.bookStore.service.BookService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author yuanlei
 * @date 2020-11-03
 */
@Controller
@RequestMapping(value="/book")
public class BookController {
    @Autowired
    RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法
    @Autowired
    BookService bookService;

    @RequestMapping(value="/test",method = RequestMethod.GET)
    @ResponseBody
    public String test(){
        return "Hello SpringBoot!";
    }

    @RequestMapping(value="/sendDirectMessage",method = RequestMethod.GET)
    @ResponseBody
    public String sendDirectMessage(String messageData) {
        String messageId = String.valueOf(UUID.randomUUID());
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("title","测试书籍"+messageId);
        map.put("price",10.2);
        map.put("publishDate",createTime);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", map);
        return "ok";
    }

    @RequestMapping(value="/addBook",method = RequestMethod.POST)
    @ResponseBody
    public String addBook(Book book){
        bookService.save(book);
        return "ok";
    }

    @RequestMapping(value="/getAllBooks",method = RequestMethod.GET)
    @ResponseBody
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @RequestMapping(value="/getBookById",method = RequestMethod.GET)
    @ResponseBody
    @Cacheable(value = "Books", key = "#id")
    @MethodTiming
    public Book getBookById(int id){
        return bookService.getById(id);
    }

    @RequestMapping(value="/queryBookByLimit",method = RequestMethod.GET)
    @ResponseBody
    public List<Book> queryBookByLimit(int start, int limit){
        return bookService.queryBookByLimit(start,limit);
    }
}
