package com.message.messagesrv.controller;


import cn.hutool.core.bean.BeanUtil;
import com.message.common.domin.BlackList;
import com.message.common.domin.bo.BlackListBo;
import com.message.common.http.Result;
import com.message.common.service.BlackListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/system/blacklist")
@Api(tags = "黑名单模块")
public class BlackListController {


    private final BlackListService blackListService;

    @ApiOperation("添加黑名单人员")
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody BlackListBo bo) {
        BlackList blackList = new BlackList();
        BeanUtil.copyProperties(bo, blackList);
        boolean save = blackListService.save(blackList);
        return save ? Result.success("success", true) : Result.fail("error", false);
    }
}
