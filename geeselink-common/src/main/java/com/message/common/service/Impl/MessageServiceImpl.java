package com.message.common.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.message.common.domin.BlackList;
import com.message.common.domin.MessageTaskInfo;
import com.message.common.domin.bo.MessageTaskInfoBo;
import com.message.common.enums.MessageTaskInfoStatusEnum;
import com.message.common.enums.MessageTypeEnum;
import com.message.common.mapper.BlackListMapper;
import com.message.common.mapper.MessageTaskInfoMapper;
import com.message.common.service.BlackListService;
import com.message.common.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl extends ServiceImpl<MessageTaskInfoMapper, MessageTaskInfo>
        implements MessageService {

    private final MessageTaskInfoMapper messageTaskInfoMapper;

    private final BlackListMapper blackListMapper;

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

        // 任务进行保存
        return this.saveBatch(messageTaskInfos);
    }

    /**
     * 拉取消息任务
     * @return
     */
    @Override
    public List<MessageTaskInfo> pullMsgTask(Integer limit) {
        // 拉取任务
        LambdaQueryWrapper<MessageTaskInfo> messageTaskInfoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        messageTaskInfoLambdaQueryWrapper.eq(MessageTaskInfo::getStatus, MessageTaskInfoStatusEnum.STATUS_ENUM_NO_SEND.getStatusCode())
                .last("limit " + limit);
        List<MessageTaskInfo> messageTaskInfos = messageTaskInfoMapper.selectList(messageTaskInfoLambdaQueryWrapper);
        // 整合任务信息
        Set<String> receivers = messageTaskInfos.stream().map(MessageTaskInfo::getReceiver).collect(Collectors.toSet());
        // 黑名单过滤
        LambdaQueryWrapper<BlackList> blackListLambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<BlackList> blackReceivers = blackListLambdaQueryWrapper.in(BlackList::getMsgTo, receivers);
        List<BlackList> blackLists = blackListMapper.selectList(blackReceivers);
        Set<String> blackTos = blackLists.stream().map(BlackList::getMsgTo).collect(Collectors.toSet());
        for (MessageTaskInfo messageTaskInfo : messageTaskInfos) {
            if (blackTos.contains(messageTaskInfo.getReceiver())) {
                messageTaskInfo.setStatus(MessageTaskInfoStatusEnum.STATUS_ENUM_SEND_BLACK.getStatusCode());
            } else {
                messageTaskInfo.setStatus(MessageTaskInfoStatusEnum.STATUS_ENUM_SENDING.getStatusCode());
            }
        }
        return messageTaskInfos;
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
