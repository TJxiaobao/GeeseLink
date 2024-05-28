package com.message.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.message.common.domin.MessageRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageRecordMapper extends BaseMapper<MessageRecord> {
}
