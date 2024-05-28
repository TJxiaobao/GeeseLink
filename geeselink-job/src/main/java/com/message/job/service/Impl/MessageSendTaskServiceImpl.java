package com.message.job.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.message.common.domin.MessageRecord;
import com.message.common.domin.MessageTaskInfo;
import com.message.common.domin.MessageTaskScheduleConfig;
import com.message.common.enums.MessageTaskInfoStatusEnum;
import com.message.common.enums.MessageTypeEnum;
import com.message.common.service.MessageRecordService;
import com.message.common.service.MessageTaskInfoService;
import com.message.job.dispatch.WorkPool;
import com.message.job.service.MessageSendTaskService;
import com.message.job.task.AsyncExecute;
import com.message.job.task.strategy.SendStrategyFactory;
import com.message.job.task.type.AbstractSend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
@Slf4j
public class MessageSendTaskServiceImpl implements MessageSendTaskService {

    private final MessageRecordService messageRecordService;
    private final MessageTaskInfoService messageTaskInfoService;

    private MessageTaskScheduleConfig config;

    private final WorkPool workPool;

    public MessageSendTaskServiceImpl(MessageRecordService messageRecordService, MessageTaskInfoService messageTaskInfoService, MessageTaskScheduleConfig config, WorkPool workPool) {
        this.messageRecordService = messageRecordService;
        this.messageTaskInfoService = messageTaskInfoService;
        this.workPool = workPool;
        this.config = config;
    }


    @Override
    public void processMessageTasks() {
        // 使用配置信息进行任务处理
        int limit = config.getMessageScheduleLimit();
        int maxRetry = config.getMaxRetryNum();
        log.info("拉取一次任务:\nlimit:" + limit + "\nmaxRetry:" + maxRetry);

        // 拉取任务
        LambdaQueryWrapper<MessageTaskInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MessageTaskInfo::getStatus, MessageTaskInfoStatusEnum.STATUS_ENUM_NO_SEND.getStatusCode())
                .last("LIMIT " + limit);
        List<MessageTaskInfo> messageTaskInfos = messageTaskInfoService.list(queryWrapper);
        log.info("任务信息:" + messageTaskInfos);

        ArrayList<Future<MessageTaskInfo>> futures = new ArrayList<>();
        // 执行任务
        for (MessageTaskInfo task : messageTaskInfos) {
            Future<MessageTaskInfo> messageTaskInfoFuture = workPool.submitJob(new AsyncExecute(task));
            task.setStatus(MessageTaskInfoStatusEnum.STATUS_ENUM_SENDING.getStatusCode());
            futures.add(messageTaskInfoFuture);
        }
        messageTaskInfoService.updateBatchById(messageTaskInfos);
        // 任务信息刷库
        // 遍历futures列表
        ArrayList<MessageRecord> messageRecords = new ArrayList<>();
        ArrayList<MessageTaskInfo> messageTaskInfosUpdate = new ArrayList<>();
        for (Future<MessageTaskInfo> future : futures) {
            try {
                // 获取异步执行的结果，设置最大等待时间为1秒
                MessageTaskInfo messageTaskInfo = future.get();
                log.info("任务结果:" + messageTaskInfo);
                MessageRecord messageRecord = new MessageRecord();
                BeanUtils.copyProperties(messageTaskInfo, messageRecord, "id");
                // 将MessageTaskInfo对象添加到新列表中
                messageRecords.add(messageRecord);
                messageTaskInfosUpdate.add(messageTaskInfo);
            } catch (InterruptedException | ExecutionException e) {
                // 处理异常情况
                log.error("MessageTaskInfo execute error: {}", e.getMessage());
            }
        }
        messageTaskInfoService.updateBatchById(messageTaskInfosUpdate);
        messageRecordService.saveBatch(messageRecords);
    }


    @Override
    public Boolean addTaskInfo(MessageTaskInfo messageTaskInfo) {
        return messageTaskInfoService.save(messageTaskInfo);
    }

    @Override
    public MessageTaskInfo doTaskSynchronization(MessageTaskInfo messageTaskInfo) {
        return send(messageTaskInfo);
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
