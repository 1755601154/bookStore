package com.example.bookStore.process;

import lombok.SneakyThrows;
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

/**
 * @author yuanlei
 * @date 2020-10-31
 */
@Slf4j
public class DirectProducter implements DisposableBean,Runnable {

    @Autowired
    RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法
    private Thread thread;
    private volatile boolean someCondition = true;
    private int count = 1;

    public DirectProducter() {
        this.thread = new Thread(this);
        this.thread.start();
        log.error("线程启动!");
    }

    @SneakyThrows
    @Override
    public void run() {
        while (someCondition) {
            //执行任务
            log.info("第"+count+"次推送消息");
            sendDirectMessage();
            //Thread.sleep(1*1000L);
            count++;
            if(count>1000000){
                someCondition=false;
            }
        }

    }

    public String sendDirectMessage() {
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

    @Override
    public void destroy() throws Exception {
        someCondition = false;
    }
}
