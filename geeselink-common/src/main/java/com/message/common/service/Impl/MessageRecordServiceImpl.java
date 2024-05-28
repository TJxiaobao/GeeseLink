package com.message.common.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.message.common.domin.MessageRecord;
import com.message.common.mapper.MessageRecordMapper;
import com.message.common.service.MessageRecordService;
import org.springframework.stereotype.Service;

@Service
public class MessageRecordServiceImpl extends ServiceImpl<MessageRecordMapper, MessageRecord>
        implements MessageRecordService {
}
