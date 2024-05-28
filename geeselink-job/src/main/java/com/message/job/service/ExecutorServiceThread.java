package com.message.job.service;

import com.message.job.service.Impl.MessageSendTaskServiceImpl;
import com.message.job.utils.ApplicationContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;

@Slf4j
@DependsOn({"ApplicationContextUtil", "messageSendTaskServiceImpl"})
public class ExecutorServiceThread implements Runnable {

    private final MessageSendTaskServiceImpl messageSendTaskService = (MessageSendTaskServiceImpl) ApplicationContextUtil.getBean("messageSendTaskServiceImpl");


    @Override
    public void run() {
        messageSendTaskService.processMessageTasks();
    }

}
