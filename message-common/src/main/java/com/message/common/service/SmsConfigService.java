package com.message.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.message.common.domin.SmsConfig;
import com.message.common.domin.bo.SmsConfigSelectBo;

import java.util.List;


public interface SmsConfigService extends IService<SmsConfig> {
    List<SmsConfig> getList(SmsConfigSelectBo bo);
}
