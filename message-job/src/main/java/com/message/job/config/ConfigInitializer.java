package com.message.job.config;

import com.message.common.domin.MessageTaskScheduleConfig;
import com.message.common.mapper.MessageTaskScheduleConfigMapper;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ConfigInitializer implements ApplicationListener<ContextRefreshedEvent> {
    private final MessageTaskScheduleConfigMapper configMapper;

    private final MessageTaskScheduleConfig config;

    public ConfigInitializer(MessageTaskScheduleConfigMapper configMapper) {
        this.configMapper = configMapper;
        this.config = new MessageTaskScheduleConfig();
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 从数据库中获取配置值
        MessageTaskScheduleConfig dbConfig = configMapper.selectOne(null);

        // 将配置值注入到配置类中
        if (dbConfig != null) {
            config.setMessageScheduleLimit(dbConfig.getMessageScheduleLimit());
            config.setMaxRetryNum(dbConfig.getMaxRetryNum());
            config.setCreateTime(dbConfig.getCreateTime());
            config.setUpdateTime(dbConfig.getUpdateTime());
        }
    }

    @Bean
    public MessageTaskScheduleConfig messageTaskScheduleConfig() {
        return this.config;
    }
}