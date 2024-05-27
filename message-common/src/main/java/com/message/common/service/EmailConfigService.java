package com.message.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.message.common.domin.EmailConfig;
import com.message.common.domin.bo.EmailConfigSelectBo;

import java.util.List;

public interface EmailConfigService extends IService<EmailConfig> {

    List<EmailConfig> getList(EmailConfigSelectBo bo);
}
