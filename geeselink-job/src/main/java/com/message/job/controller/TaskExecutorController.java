package com.message.job.controller;

import cn.hutool.core.bean.BeanUtil;
import com.message.common.domin.MessageRecord;
import com.message.common.domin.MessageTaskInfo;
import com.message.common.domin.bo.MessageTaskInfoBo;
import com.message.common.enums.MessageTaskInfoStatusEnum;
import com.message.common.http.Result;
import com.message.common.service.MessageRecordService;
import com.message.job.service.MessageSendTaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/job/task")
public class TaskExecutorController {

    @Autowired
    private MessageSendTaskService messageSendTaskService;

    @Autowired
    private MessageRecordService messageRecordService;

    @PostMapping("/executeTask")
    public Result<Boolean> executeTask(@RequestBody MessageTaskInfoBo bo) {

        // 执行任务并保存任务记录
        MessageTaskInfo messageTaskInfo = BeanUtil.copyProperties(bo, MessageTaskInfo.class);
        messageTaskInfo.setStatus(MessageTaskInfoStatusEnum.STATUS_ENUM_SENDING.getStatusCode());
        MessageTaskInfo resultMessageTaskInfo = messageSendTaskService.doTaskSynchronization(messageTaskInfo);
        MessageRecord messageRecord = new MessageRecord();
        BeanUtils.copyProperties(resultMessageTaskInfo, messageRecord, "id");
        // 将MessageTaskInfo对象添加到新列表中
        messageRecordService.save(messageRecord);
        return Result.success("任务执行成功", true);
    }

    @PostMapping("/addTaskInfo")
    public Result<Boolean> addTaskInfo(@RequestBody MessageTaskInfoBo bo) {
        MessageTaskInfo messageTaskInfo = BeanUtil.copyProperties(bo, MessageTaskInfo.class);
        Boolean b = messageSendTaskService.addTaskInfo(messageTaskInfo);
        return Result.success("创建任务成功", b);
    }
}
