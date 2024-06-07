package com.message.common.domin.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("短信配置 VO")
public class SmsConfigVo {
    /**
     * 配置 ID
     */
    private String id;

    /**
     * 配置 ID
     */
    private String configId;

    /**
     * 运营商
     */
    private String supplier;

    /**
     * accessKeyId
     */
    private String accessKeyId;

    /**
     * accessKeySecret
     */
    private String accessKeySecret;

    /**
     * 签名
     */
    private String signature;

    /**
     * 模版 ID
     */
    private String templateId;

    private Date createdAt;

    private Date updatedAt;
}
