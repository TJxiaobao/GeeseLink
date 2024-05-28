package com.message.common.domin.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("模版BO")
public class TemplateBo {
    /**
     * 模版名称
     */
    @ApiModelProperty(value = "模版名称", required = true, example = "模版名称")
    private String templateName;

    /**
     * 状态 0 启用 2 禁用
     */
    @ApiModelProperty(value = "状态 0 启用 2 禁用", required = true, example = "0")
    private Integer status;

    /**
     * 模版类型
     */
    @ApiModelProperty(value = "模版类型", required = true, example = "1")
    private int type;

    /**
     * 模版配置
     */
    @ApiModelProperty(value = "模版配置", required = true, example = "{\"title\":\"模版标题\",\"content\":\"模版内容\"}")
    private String content;

    /**
     * 说明
     */
    @ApiModelProperty(value = "说明", required = true, example = "说明")
    private String description;
}
