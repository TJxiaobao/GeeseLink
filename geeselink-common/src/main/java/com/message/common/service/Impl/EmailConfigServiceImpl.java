package com.message.common.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.message.common.domin.EmailConfig;
import com.message.common.domin.bo.EmailConfigSelectBo;
import com.message.common.mapper.EmailConfigMapper;
import com.message.common.service.EmailConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailConfigServiceImpl extends ServiceImpl<EmailConfigMapper, EmailConfig>
        implements EmailConfigService {
    @Override
    public List<EmailConfig> getList(EmailConfigSelectBo bo) {
        IPage<EmailConfig> page = new Page<>(bo.getPageNum(), bo.getPageSize());

        LambdaQueryWrapper<EmailConfig> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(!ObjectUtils.isEmpty(bo.getConfigId()), EmailConfig::getConfigId, bo.getConfigId());
        queryWrapper.like(!ObjectUtils.isEmpty(bo.getHost()), EmailConfig::getHost, bo.getHost());
        queryWrapper.like(!ObjectUtils.isEmpty(bo.getPort()), EmailConfig::getPort, bo.getPort());
        queryWrapper.like(!ObjectUtils.isEmpty(bo.getProtocol()), EmailConfig::getProtocol, bo.getProtocol());
        queryWrapper.like(!ObjectUtils.isEmpty(bo.getUsername()), EmailConfig::getUsername, bo.getUsername());
        if (!ObjectUtils.isEmpty(bo.getCreatedAtStart()) && !ObjectUtils.isEmpty(bo.getCreatedAtEnd())) {
            queryWrapper.between(EmailConfig::getCreatedAt, bo.getCreatedAtStart(), bo.getCreatedAtEnd());

        }
        queryWrapper.eq(EmailConfig::getIsDeleted, 0);
        return this.list(page, queryWrapper);
    }
}
