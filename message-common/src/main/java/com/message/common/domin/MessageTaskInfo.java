package com.message.common.domin;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 消息任务信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "message_task_info")
public class MessageTaskInfo {

    /**
     * 消息任务 ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 业务Id
     */
    private String bizId;

    /**
     * 消息类型 0 邮件, 1 阿里云短信, 2 腾讯云短信.....
     */
    private int msgTaskType;

    /**
     * 业务类型
     */
    private int bizType;

    /**
     * 任务状态 0 未发送, 1 发送中, 2 发送成功, 3 发送失败
     */
    private int status;

    /**
     * 最大重试次数
     */
    private int maxRetryNum;

    /**
     * 已经重试次数
     */
    private int crtRetryNum;

    /**
     * 重试间隔
     */
    private int retryInterval;

    /**
     * message 标题
     */
    private String title;

    /**
     * message 内容
     */
    private String content;

    /**
     * message 接受人
     */
    private String receiver;

    /**
     * 配置 ID
     */
    private String configId;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;
}
