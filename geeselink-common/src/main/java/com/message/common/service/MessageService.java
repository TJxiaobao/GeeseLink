package com.message.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.message.common.domin.MessageTaskInfo;
import com.message.common.domin.bo.MessageTaskInfoBo;

import java.util.List;

public interface MessageService extends IService<MessageTaskInfo> {

    Boolean addMsg(MessageTaskInfoBo messageTaskInfoBo);

    List<MessageTaskInfo> pullMsgTask(Integer limit);
}
