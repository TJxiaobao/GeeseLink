package com.message.common.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.message.common.domin.SmsConfig;
import com.message.common.domin.bo.SmsConfigSelectBo;
import com.message.common.mapper.SmsConfigMapper;
import com.message.common.service.SmsConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SmsConfigServiceImpl extends ServiceImpl<SmsConfigMapper, SmsConfig>
        implements SmsConfigService {
    @Override
    public List<SmsConfig> getList(SmsConfigSelectBo bo) {
        IPage<SmsConfig> page = new Page<>(bo.getPageNum(), bo.getPageSize());

        LambdaQueryWrapper<SmsConfig> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(!ObjectUtils.isEmpty(bo.getConfigId()), SmsConfig::getConfigId, bo.getConfigId());
        queryWrapper.like(!ObjectUtils.isEmpty(bo.getSupplier()), SmsConfig::getSupplier, bo.getSupplier());
        if (!ObjectUtils.isEmpty(bo.getCreatedAtStart()) && !ObjectUtils.isEmpty(bo.getCreatedAtEnd())) {
            queryWrapper.between(SmsConfig::getCreatedAt, bo.getCreatedAtStart(), bo.getCreatedAtEnd());
        }
        if (!ObjectUtils.isEmpty(bo.getUpdatedAtStart()) && !ObjectUtils.isEmpty(bo.getUpdatedAtEnd())) {
            queryWrapper.between(SmsConfig::getUpdatedAt, bo.getUpdatedAtStart(), bo.getUpdatedAtEnd());
        }
        queryWrapper.eq(SmsConfig::getIsDeleted, 0);
        return this.list(page, queryWrapper);
    }
}
