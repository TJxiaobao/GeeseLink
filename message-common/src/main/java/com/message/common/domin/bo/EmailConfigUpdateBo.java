package com.message.common.domin.bo;

import com.sun.istack.internal.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("邮件配置更新 BO")
public class EmailConfigUpdateBo {


    /**
     * 配置 ID
     */
    @ApiModelProperty(value = "主键 ID", required = true, example = "1")
    @NotNull
    private Long id;

    /**
     * 配置 ID
     */
    @ApiModelProperty(value = "配置 ID", required = true, example = "1")
    @NotNull
    private String configId;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", required = true, example = "123456")
    @NotNull
    private String username;

    /**
     * 授权码
     */
    @NotNull
    @ApiModelProperty(value = "授权码", required = true, example = "test")
    private String password;

    /**
     * host
     */
    @NotNull
    @ApiModelProperty(value = "host", required = true, example = "127.0.0.1")
    private String host;

    /**
     * 端口
     */
    @NotNull
    @ApiModelProperty(value = "端口", required = true, example = "25")
    private Integer port;

    /**
     * 协议
     */
    @NotNull
    @ApiModelProperty(value = "协议", required = true, example = "smtp")
    private String protocol;

    /**
     * 默认编码
     */
    @NotNull
    @ApiModelProperty(value = "默认编码", required = true, example = "UTF-8")
    private String defaultEncoding;
}