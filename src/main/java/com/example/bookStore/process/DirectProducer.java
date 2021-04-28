package com.example.bookStore.process;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yuanlei
 * @date 2020-10-31
 */
@Slf4j
public class DirectProducer implements DisposableBean{

    @Autowired
    private RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法
    private ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    private volatile int count = 0;
    public static final int DEFAULT_CONCURRENT = 10;//并发数量

    public DirectProducer() {
        for(int i=0;i<DEFAULT_CONCURRENT;i++) {
            cachedThreadPool.submit(() -> {
                count++;
                while (count <= 100000) {
                    log.debug("current message count:{}", count);
                    sendDirectMessage();
                    count++;
                }
            });
        }
        log.info("mq消息推送线程启动!");
    }

    public String sendDirectMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("title",messageId);
        map.put("price",10.2);
        map.put("publishDate",createTime);
        log.debug("put message:{}",map.toString());
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", map);
        return "ok";
    }

    @Override
    public void destroy() throws Exception {
        cachedThreadPool.shutdown();
    }
}
