package com.message.monitor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.message.common.mapper")
@ComponentScan(value = {"com.message.common"})
public class MessageMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageMonitorApplication.class, args);
    }

}
