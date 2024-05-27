package com.message.job.listener;

import org.dromara.sms4j.core.datainterface.SmsReadConfig;
import org.dromara.sms4j.core.factory.SmsFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SmsConfigListener {

    @Autowired
    SmsReadConfig config;

    @EventListener
    public void init(ContextRefreshedEvent event) {
        // 创建 SmsBlend 短信实例
        SmsFactory.createSmsBlend(config);
    }
}