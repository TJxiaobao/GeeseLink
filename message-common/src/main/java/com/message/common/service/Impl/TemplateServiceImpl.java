package com.message.common.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.message.common.domin.MessageTemplate;
import com.message.common.domin.bo.TemplateBo;
import com.message.common.mapper.MessageTemplateMapper;
import com.message.common.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TemplateServiceImpl extends ServiceImpl<MessageTemplateMapper, MessageTemplate>
        implements TemplateService {

    private final MessageTemplateMapper messageTemplateMapper;

    @Override
    public Boolean addTemplate(TemplateBo templateBo) {
        MessageTemplate messageTemplate = BeanUtil.toBean(templateBo, MessageTemplate.class);
        return messageTemplateMapper.insert(messageTemplate) > 0;
    }
}
