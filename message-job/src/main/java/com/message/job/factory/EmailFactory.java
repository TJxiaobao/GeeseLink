package com.message.job.factory;

import com.message.common.domin.EmailBlend;
import org.dromara.sms4j.api.SmsBlend;
import org.dromara.sms4j.comm.exception.SmsBlendException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public abstract class EmailFactory {

    private static final Map<String, EmailBlend> emailConfigMap = new ConcurrentHashMap<>();

    public static List<SmsBlend> getAll() {
        return new ArrayList(emailConfigMap.values());
    }

    public static EmailBlend getEmailBlend(String configId) {
        return emailConfigMap.get(configId);
    }

    public static void register(EmailBlend emailBlend) {
        if (emailBlend == null) {
            throw new SmsBlendException("邮件发送对象不能为空");
        } else {
            emailConfigMap.put(emailBlend.getConfigId(), emailBlend);
        }
    }

}
