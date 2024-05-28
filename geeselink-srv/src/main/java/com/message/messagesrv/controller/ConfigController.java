package com.message.messagesrv.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.message.common.domin.EmailConfig;
import com.message.common.domin.SmsConfig;
import com.message.common.domin.bo.EmailConfigInsertBo;
import com.message.common.domin.bo.EmailConfigSelectBo;
import com.message.common.domin.bo.EmailConfigUpdateBo;
import com.message.common.domin.bo.SmsConfigInsertBo;
import com.message.common.domin.bo.SmsConfigSelectBo;
import com.message.common.domin.bo.SmsConfigUpdateBo;
import com.message.common.domin.excel.EmailConfigExcelData;
import com.message.common.domin.excel.SmsConfigExcelData;
import com.message.common.http.Result;
import com.message.common.service.EmailConfigService;
import com.message.common.service.SmsConfigService;
import com.message.common.util.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/system/config")
@Api(tags = "系统配置")
public class ConfigController {

    private final EmailConfigService emailConfigService;

    private final SmsConfigService smsConfigService;

    @ApiOperation("添加邮箱配置")
    @PostMapping("/add/email")
    public Result<EmailConfig> addEmail(@ApiParam(value = "邮件配置类", required = true) @RequestBody EmailConfigInsertBo emailConfigInsertBo) {
        EmailConfig emailConfig = BeanUtil.copyProperties(emailConfigInsertBo, EmailConfig.class);
        emailConfigService.save(emailConfig);
        return Result.success("success", emailConfig);
    }

    @ApiOperation("添加短信配置")
    @PostMapping("/add/sms")
    public Result<SmsConfig> addSms(@RequestBody SmsConfigInsertBo smsConfigInsertBo) {
        SmsConfig smsConfig = BeanUtil.copyProperties(smsConfigInsertBo, SmsConfig.class);
        smsConfigService.save(smsConfig);
        return Result.success("success", smsConfig);
    }

    @ApiOperation("更新邮箱配置")
    @PostMapping("/update/email")
    public Result<EmailConfig> updateEmail(@RequestBody EmailConfigUpdateBo emailConfigBo) {
        EmailConfig emailConfig = BeanUtil.copyProperties(emailConfigBo, EmailConfig.class);
        emailConfigService.updateById(emailConfig);
        return Result.success("success", emailConfig);
    }

    @ApiOperation("更新短信配置")
    @PostMapping("/update/sms")
    public Result<SmsConfig> updateEmail(@RequestBody SmsConfigUpdateBo smsConfigBo) {
        SmsConfig smsConfig = BeanUtil.copyProperties(smsConfigBo, SmsConfig.class);
        smsConfigService.updateById(smsConfig);
        return Result.success("success", smsConfig);
    }

    @ApiOperation("删除邮箱配置")
    @DeleteMapping("/del/email")
    public Result<Boolean> delEmail(@RequestParam("id") String id) {

        emailConfigService.removeById(Long.parseLong(id));
        return Result.success("success", true);
    }

    @ApiOperation("删除短信配置")
    @DeleteMapping("/del/sms")
    public Result<Boolean> delSms(@RequestParam("id") String id) {
        smsConfigService.removeById(Long.parseLong(id));
        return Result.success("success", true);
    }

    @ApiOperation("查询单个邮箱配置")
    @GetMapping("/get_info/email")
    public Result<EmailConfig> getInfoEmail(@RequestParam("id") String id) {
        EmailConfig data = emailConfigService.getById(Long.parseLong(id));
        return Result.success("success", data);
    }

    @ApiOperation("查询单个短信配置")
    @GetMapping("/get_info/sms")
    public Result<SmsConfig> getInfoSms(@RequestParam("id") String id) {
        SmsConfig data = smsConfigService.getById(Long.parseLong(id));
        return Result.success("success", data);
    }

    @ApiOperation("查询邮箱配置")
    @GetMapping("/list/email")
    public Result<List<EmailConfig>> listEmail(EmailConfigSelectBo bo) {
        List<EmailConfig> data = emailConfigService.getList(bo);
        return Result.success("success", data);
    }

    @ApiOperation("查询短信配置")
    @GetMapping("/list/sms")
    public Result<List<SmsConfig>> listSms(SmsConfigSelectBo bo) {
        List<SmsConfig> data = smsConfigService.getList(bo);
        return Result.success("success", data);
    }

    @ApiOperation("导出邮件配置")
    @PostMapping("/export/email")
    public Result<Boolean> exportEmail() {
        LambdaQueryWrapper<EmailConfig> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EmailConfig::getIsDeleted, 0);
        List<EmailConfig> data = emailConfigService.list(queryWrapper);
        ExcelUtil.writeExcel("", EmailConfigExcelData.class, "邮件配置", data);
        return Result.success("success", true);
    }

    @ApiOperation("导出短信配置")
    @PostMapping("/export/sms")
    public Result<Boolean> exportSms() {
        LambdaQueryWrapper<SmsConfig> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SmsConfig::getIsDeleted, 0);
        List<SmsConfig> data = smsConfigService.list(queryWrapper);
        ExcelUtil.writeExcel("", SmsConfigExcelData.class, "短信配置", data);
        return Result.success("success", true);
    }
}
