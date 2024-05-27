package com.message.common.domin;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 调度配置信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "message_task_schedule_config")
public class MessageTaskScheduleConfig {

    /**
     * 每次拉取任务数量
     */
    private int messageScheduleLimit = 2;

    /**
     * 最大重试次数
     */
    private int maxRetryNum = 2;

    /**
     * 重试间隔
     */
    private int retryInterval = 1;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;
}
