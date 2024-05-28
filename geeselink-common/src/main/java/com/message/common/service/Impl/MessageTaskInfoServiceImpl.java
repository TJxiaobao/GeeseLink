package com.message.common.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.message.common.domin.MessageTaskInfo;
import com.message.common.mapper.MessageTaskInfoMapper;
import com.message.common.service.MessageTaskInfoService;
import org.springframework.stereotype.Service;


@Service
public class MessageTaskInfoServiceImpl extends ServiceImpl<MessageTaskInfoMapper, MessageTaskInfo> implements MessageTaskInfoService {
}
