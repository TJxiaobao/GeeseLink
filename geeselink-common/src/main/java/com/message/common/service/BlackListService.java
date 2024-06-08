package com.message.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.message.common.domin.BlackList;

public interface BlackListService extends IService<BlackList> {

    Boolean addBlackList(BlackList blackList);
}
