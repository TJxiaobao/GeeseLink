package com.message.common.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.message.common.domin.BlackList;
import com.message.common.mapper.BlackListMapper;
import com.message.common.service.BlackListService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlackListServiceImpl extends ServiceImpl<BlackListMapper, BlackList> implements BlackListService {

    // 注入 RedissonClient
    @Autowired
    RedissonClient redissonClient;

    @Override
    public Boolean addBlackList(BlackList blackList) {
        // 先插入MySQL
        boolean save = this.save(blackList);
        if (save) {
            // 再插入Redis 通过位图
            return redissonClient.getBitSet("blackList").set(blackList.getId(), true);
        }
        return true;
    }
}
