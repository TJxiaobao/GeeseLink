package com.message.job.config;

import com.message.common.domin.SmsConfig;
import com.message.common.mapper.SmsConfigMapper;
import com.message.job.utils.ConfigMapUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class SmsConfigInitializer implements ApplicationRunner {

    private final SmsConfigMapper smsConfigMapper;

    public SmsConfigInitializer(SmsConfigMapper smsConfigMapper) {
        this.smsConfigMapper = smsConfigMapper;
    }

    @Override
    public void run(ApplicationArguments args) {
        List<SmsConfig> configs = smsConfigMapper.selectList(null);
        Map<String, SmsConfig> map = new HashMap<>();
        for (SmsConfig config : configs) {
            map.put(config.getConfigId(), config);
        }
        ConfigMapUtils.initialize(map);
    }
}