package com.message.messagesrv.controller;

import com.message.common.domin.bo.TemplateBo;
import com.message.common.http.Result;
import com.message.common.service.TemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/system/template")
@Api(tags = "系统-模板管理")
public class TemplateController {

    private final TemplateService templateService;

    @ApiOperation("新增模板")
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody TemplateBo bo) {
        Boolean flag = templateService.addTemplate(bo);
        if (flag) {
            return Result.success("success", true);
        } else {
            return Result.fail("error", false);
        }

    }
}
