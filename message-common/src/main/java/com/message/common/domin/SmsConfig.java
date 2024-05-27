package com.message.common.domin;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sms_config")
public class SmsConfig {

    /**
     * 配置 ID
     */
    @TableId(value = "id")
    private Long id;

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

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private Date createdAt;

    @TableField(value = "updated_at", fill = FieldFill.UPDATE)
    private Date updatedAt;

    @TableLogic
    @TableField("is_deleted")
    private Integer isDeleted;
}
