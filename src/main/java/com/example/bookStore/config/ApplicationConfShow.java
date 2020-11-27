package com.example.bookStore.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author yuanlei
 * 测试读取application.properties中的配置
 * @date 2020-11-03
 */
@Component
public class ApplicationConfShow {

    @Value("${server.port}")     private int intPort;

    public void show(){
        System.out.println("===========================================");
        System.out.println("intPort :   " +intPort);
        System.out.println("===========================================");
    }
}
