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
@ApiModel("短信配置新增 BO")
public class SmsConfigInsertBo {

    /**
     * 配置 ID
     */
    @ApiModelProperty(value = "配置 ID", required = true, example = "1")
    @NotNull
    private String configId;

    /**
     * 运营商
     */
    @ApiModelProperty(value = "运营商", required = true, example = "alibaba")
    @NotNull
    private String supplier;

    /**
     * accessKeyId
     */
    @ApiModelProperty(value = "accessKeyId", required = true, example = "123456789")
    @NotNull
    private String accessKeyId;

    /**
     * accessKeySecret
     */
    @ApiModelProperty(value = "accessKeySecret", required = true, example = "123456789")
    @NotNull
    private String accessKeySecret;

    /**
     * 签名
     */
    @ApiModelProperty(value = "签名", required = true, example = "123456789")
    @NotNull
    private String signature;

    /**
     * 模版 ID
     */
    @ApiModelProperty(value = "模版 ID", required = true, example = "123456789")
    @NotNull
    private String templateId;
}
