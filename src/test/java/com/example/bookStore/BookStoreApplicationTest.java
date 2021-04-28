package com.example.bookStore;

import com.example.bookStore.entity.Book;
import com.example.bookStore.util.ApplicationContextUtil;
import com.example.bookStore.util.ZipUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author yuanlei
 * @description
 * @date 2021-03-01
 */
@Slf4j
@SpringBootApplication
public class BookStoreApplicationTest {
    public static void main(String[] args){
        SpringApplication.run(BookStoreApplicationTest.class, args);
        ApplicationContext applicationContext = ApplicationContextUtil.getApplicationContext();
        log.info("test!");
    }
    /**
     * @Author yuanlei
     * @Description //测试生成zip文件
     * @Date 10:43 2021/4/8
     * @Param []
     * @return void
     **/
    @Test
    public void testCreateZipFile(){
        File file = new File("README.md");
        File[] files = new File[1];
        files[0] = file;
        ZipUtils.zipFile("README.md.zip",files);
    }
    /**
     * @Author yuanlei
     * @Description //测试集合的使用
     * @Date 10:45 2021/4/8
     * @Param []
     * @return void
     **/
    @Test
    public void testCollections(){
        Book book = new Book();
        ArrayList<Book> list = new ArrayList<>();
        ConcurrentHashMap<String,Book> map = new ConcurrentHashMap<>();
        map.put("1",book);
        map.get("1");
    }
}
