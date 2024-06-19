package com.message.srv;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.message.common.mapper")
@ComponentScan(value = {"com.message.common", "com.message.srv"})
public class MessageSrvApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageSrvApplication.class, args);
    }

}
