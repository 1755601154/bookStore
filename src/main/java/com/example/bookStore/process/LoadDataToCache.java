package com.example.bookStore.process;

import com.example.bookStore.annotation.MethodTiming;
import com.example.bookStore.entity.Book;
import com.example.bookStore.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * @author yuanlei
 * 实现ApplicationRunner接口，工程启动完毕后自动执行run方法，用于加载缓存数据
 * @date 2020-11-20
 */
@Slf4j
public class LoadDataToCache implements ApplicationRunner {
    @Autowired
    private BookService bookService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @Author yuanlei
     * @Description //工程启动完毕，自动调用该方法
     * @Date 16:02 2020/11/21
     * @Param [args]
     * @return void
     **/
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("工程启动完毕!");
    }

    /**
     * @Author yuanlei
     * @Description //定时任务，每5分钟刷新全量加载一次缓存
     * @Date 16:02 2020/11/21
     * @Param []
     * @return java.util.List<com.example.bookStore.entity.Book>
     **/
    @Scheduled(fixedRate=3000*1000l)
    @MethodTiming
    public void loadData(){
        log.info("开始加载数据进redis");
        int count = bookService.count();
        //分批次查询数据，每次最多900000条数据，避免内存泄漏
        int limit = 900000;
        int num = count/limit;
        int ds = count%limit;
        for(int i=0;i<=num;i++){
            int start=i*limit;
            if(i==num){
                limit=ds;
            }
            List<Book> results= bookService.queryBookByLimit(start,limit);
            for(Book book:results){
                redisTemplate.opsForValue().set("Books::"+book.getId().toString(),book);
            }
        }
        log.info("加载数据完毕!");
    }
}
