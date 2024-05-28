package com.message.job.task;

import com.message.common.domin.MessageTaskInfo;
import com.message.common.enums.MessageTaskInfoStatusEnum;
import com.message.common.enums.MessageTypeEnum;
import com.message.job.service.MessageSendTaskService;
import com.message.job.task.strategy.SendStrategyFactory;
import com.message.job.task.type.AbstractSend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

@Slf4j
@Component
public class AsyncExecute implements Callable<MessageTaskInfo> {

    private MessageTaskInfo messageTaskInfo;

    private MessageSendTaskService messageSendTaskService;

    public AsyncExecute() {
    }

    public AsyncExecute(MessageTaskInfo messageTaskInfo) {
        this.messageTaskInfo = messageTaskInfo;
    }

    @Override
    public MessageTaskInfo call() throws Exception {
        try {
            log.info("执行信息类型为" + messageTaskInfo.getMsgTaskType() + "任务id" + messageTaskInfo.getId());
            return send(messageTaskInfo);
        } catch (Exception e) {
            log.error("id为" + messageTaskInfo.getId() + "的任务执行失败,抛出异常" + e);
            throw new RuntimeException(e);
        }
    }

    public MessageTaskInfo send(MessageTaskInfo messageTaskInfo) {
        // todo 实现发送业务
        messageTaskInfo.setCrtRetryNum(messageTaskInfo.getCrtRetryNum() + 1);
        messageTaskInfo.setStatus(MessageTaskInfoStatusEnum.STATUS_ENUM_SENDING.getStatusCode());
        String statusName = getStatusName(messageTaskInfo.getMsgTaskType());
        AbstractSend sendMessage = SendStrategyFactory.invoke(statusName);
        MessageTaskInfo messageTaskInfoByUpdate = sendMessage.send(messageTaskInfo, messageTaskInfo.getConfigId());
        // todo 更多消息业务
        return messageTaskInfoByUpdate;
    }

    private String getStatusName(int type) {
        if (MessageTypeEnum.SMS.getStatusCode() == type) {
            return MessageTypeEnum.SMS.getStatusName();
        } else if (MessageTypeEnum.EMAIL.getStatusCode() == type) {
            return MessageTypeEnum.EMAIL.getStatusName();
        } else {
            return MessageTypeEnum.SMS.getStatusName();
        }
    }
}
