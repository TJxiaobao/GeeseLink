package com.message.common.domin.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("消息任务信息 BO")
public class MessageTaskInfoBo {


    /**
     * message 标题
     */
    @ApiModelProperty(value = "标题", required = true, example = "标题")
    private String title;

    /**
     * message 内容
     */
    @ApiModelProperty(value = "内容", required = true, example = "内容")
    private String content;

    /**
     * message 接受人
     */
    @ApiModelProperty(value = "接受人", dataType = "List", required = true, example = "[\"1\",\"2\",\"3\",\"4\"]")
    private List<String> receiver;
    /**
     * 模版 ID (sms 需要选择)
     */
    @ApiModelProperty(value = "模版 ID", required = true, example = "abc123")
    private String configId;

    /**
     * 消息任务类型
     */
    @ApiModelProperty(value = "消息任务类型", required = true, example = "[\"1\",\"2\",\"3\",\"4\"]")
    private List<String> messageType;


    /**
     * 最大重试次数 (可选)
     */
    @ApiModelProperty(value = "最大重试次数", required = true, example = "3")
    private int maxRetryNum;

    /**
     * 重试间隔（可选）
     */
    @ApiModelProperty(value = "重试间隔", required = true, example = "10")
    private int retryInterval;

}
