package com.message.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.message.common.domin.MessageTemplate;
import com.message.common.domin.bo.TemplateBo;

public interface TemplateService extends IService<MessageTemplate> {

    Boolean addTemplate(TemplateBo templateBo);
}
