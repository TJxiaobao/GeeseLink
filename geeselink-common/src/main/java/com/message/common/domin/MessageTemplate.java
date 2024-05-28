package com.message.common.domin;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "message_template")
public class MessageTemplate {

    private Long id;

    /**
     * 模版名称
     */
    private String templateName;

    /**
     * 模版配置
     */
    private String content;

    /**
     * 状态 0 启用 2 禁用
     */
    private int status;

    /**
     * 模版类型
     */
    private int type;

    /**
     * 说明
     */
    private String description;
}
