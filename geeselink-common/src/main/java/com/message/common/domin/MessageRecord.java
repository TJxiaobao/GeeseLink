package com.message.common.domin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "message_record")
public class MessageRecord {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 业务Id
     */
    private String bizId;

    /**
     * 业务类型
     */
    private int bizType;

    /**
     * 消息类型 0 邮件, 1 阿里云短信, 2 腾讯云短信.....
     */
    private int msgType;

    /**
     * message 标题
     */
    private String title;

    /**
     * message 内容
     */
    private String Content;

    /**
     * message 状态
     */
    private int status;

    /**
     * 失败消息
     */
    private String failContext;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;
}
