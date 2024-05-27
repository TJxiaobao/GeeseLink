package com.message.common.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.message.common.domin.MessageTaskInfo;
import com.message.common.domin.bo.MessageTaskInfoBo;
import com.message.common.enums.MessageTypeEnum;
import com.message.common.mapper.MessageTaskInfoMapper;
import com.message.common.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl extends ServiceImpl<MessageTaskInfoMapper, MessageTaskInfo>
        implements MessageService {

    private final MessageTaskInfoMapper messageTaskInfoMapper;

    @Override
    @Transactional
    public Boolean addMsg(MessageTaskInfoBo messageTaskInfoBo) {
        ArrayList<MessageTaskInfo> messageTaskInfos = new ArrayList<>();
        StringBuilder receiver = new StringBuilder();
        messageTaskInfoBo.getReceiver().forEach(l -> receiver.append(l).append(","));
        String receiverStr = receiver.substring(0, receiver.length() - 1);

        for (String msgType : messageTaskInfoBo.getMessageType()) {
            MessageTaskInfo messageTaskInfo = new MessageTaskInfo();
            messageTaskInfo.setReceiver(receiverStr);
            messageTaskInfo.setMsgTaskType(getMsgTypeCode(msgType));
            BeanUtil.copyProperties(messageTaskInfoBo, messageTaskInfo);
            messageTaskInfos.add(messageTaskInfo);
        }

        // 任务进行保存 todo
        // messageTaskInfoMapper.i
        return this.saveBatch(messageTaskInfos);
    }

    /**
     * 获取类型 code
     *
     * @param msgTypeString msgType String
     * @return code
     */
    private int getMsgTypeCode(String msgTypeString) {
        if (MessageTypeEnum.EMAIL.getStatusName().equals(msgTypeString)) {
            return MessageTypeEnum.EMAIL.getStatusCode();
        } else if (MessageTypeEnum.SMS.getStatusName().equals(msgTypeString)) {
            return MessageTypeEnum.SMS.getStatusCode();
        } else {
            return -1;
        }
    }
}
