package com.message.job;

import com.message.common.domin.MessageTaskScheduleConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class JobApplicationTests {
    @Resource(lookup = "config")
    public MessageTaskScheduleConfig config;

    @Test
    void contextLoads() {
        System.out.println(config.toString());
    }

}
