package com.message.job.utils;

import com.message.common.domin.SmsConfig;

import java.util.HashMap;
import java.util.Map;

public class ConfigMapUtils {
    private static final Map<String, SmsConfig> configMap = new HashMap<>();

    public static void initialize(Map<String, SmsConfig> map) {
        configMap.clear();
        configMap.putAll(map);
    }

    public static SmsConfig getConfigByConfigId(String configId) {
        return configMap.get(configId);
    }

    public static Map<String, SmsConfig> getConfigMap() {
        return configMap;
    }
}