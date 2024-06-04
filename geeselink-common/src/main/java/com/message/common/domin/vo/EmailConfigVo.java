package com.message.common.domin.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("邮件配置 VO")
public class EmailConfigVo {

    private String id;

    /**
     * 配置 ID
     */
    private String configId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 授权码
     */
    private String password;

    /**
     * host
     */
    private String host;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 协议
     */
    private String protocol;

    /**
     * 默认编码
     */
    private String defaultEncoding;

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private Date createdAt;

    @TableField(value = "updated_at", fill = FieldFill.UPDATE)
    private Date updatedAt;

    @TableLogic
    @TableField("is_deleted")
    private Integer isDeleted;
}
