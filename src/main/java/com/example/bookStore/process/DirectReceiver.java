package com.example.bookStore.process;

import com.alibaba.fastjson.JSONObject;
import com.example.bookStore.entity.Book;
import com.example.bookStore.entity.BookBak;
import com.example.bookStore.service.BookBakService;
import com.example.bookStore.service.BookService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * @author yuanlei
 * @date 2020-11-17
 */
@RabbitListener(queues = "TestDirectQueue",containerFactory = "customContainerFactory")//监听的队列名称 TestDirectQueue
@Slf4j
@Component
public class DirectReceiver {

    @Autowired
    BookService bookService;

    @Autowired
    BookBakService bookBakService;

    @SneakyThrows
    public void process(Map testMessage) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.debug("DirectReceiver receive message  : {}",testMessage.toString());
        Book book = new Book();
        book.setTitle(testMessage.get("title").toString());
        book.setPrice(new Double((testMessage.get("price").toString())));
        book.setPublishDate(dateFormat.parse(testMessage.get("publishDate").toString()));
        bookService.save(book);

    }

    @RabbitHandler
    public void saveBookBak(String object) {
        log.debug("DirectReceiver receive message  : {}",object);
        BookBak bookBak = JSONObject.parseObject(object,BookBak.class);
        bookBak.setMessage(object);
        bookBakService.save(bookBak);
    }
}
