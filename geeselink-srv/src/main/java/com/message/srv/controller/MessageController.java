package com.message.srv.controller;

import com.message.common.domin.MessageTaskInfo;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static com.message.common.constants.HttpConstants.AUTH_REQUEST_HEADER;
import static com.message.common.constants.HttpConstants.AUTH_REQUEST_SECRET;

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

    @ApiOperation("拉取消息任务")
    @PostMapping("/pull_msg_task")
    public Result<List> pullMsgTask(Integer limit, HttpServletRequest request, HttpServletResponse response) {
        // 基本的认证
        String authHeader = request.getHeader(AUTH_REQUEST_HEADER);
        if (!AUTH_REQUEST_SECRET.equals(authHeader)) {
            response.setStatus(403);
            return null;
        }
        List<MessageTaskInfo> messageTaskInfos = messageService.pullMsgTask(limit);
        return Result.success("success", messageTaskInfos);
    }

    @ApiOperation("更新消息任务状态")
    @PostMapping("/update_msg_task")
    public Result<Boolean> updateMsgTask(List<MessageTaskInfo> messageTaskInfos, HttpServletRequest request,
                                         HttpServletResponse response) {
        // 基本的认证
        String authHeader = request.getHeader(AUTH_REQUEST_HEADER);
        if (!AUTH_REQUEST_SECRET.equals(authHeader)) {
            response.setStatus(403);
            return null;
        }
        boolean flag = messageService.updateBatchById(messageTaskInfos);
        return flag ?  Result.success("success", true) : Result.fail("fail", false);
    }
}
