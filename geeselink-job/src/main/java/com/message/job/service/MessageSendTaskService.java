package com.message.job.service;

import com.message.common.domin.MessageTaskInfo;

public interface MessageSendTaskService {

    void processMessageTasks();

    Boolean addTaskInfo(MessageTaskInfo messageTaskInfo);

    MessageTaskInfo doTaskSynchronization(MessageTaskInfo messageTaskInfo);

}
