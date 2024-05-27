package com.message.messagesrv.controller;

import com.message.common.domin.bo.MessageTaskInfoBo;
import com.message.common.http.Result;
import com.message.common.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/system/message")
@Api(tags = "消息模块")
public class MessageController {

    private final MessageService messageService;

    @ApiOperation("添加消息")
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody MessageTaskInfoBo bo) {
        messageService.addMsg(bo);
        return Result.success("success", true);
    }
}
