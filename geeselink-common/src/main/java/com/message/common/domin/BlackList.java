package com.message.common.domin;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlackList {

    private Long id;

    /**
     * 消息接收人
     */
    private String msgTo;

    /**
     * 消息类型
     */
    private String msgType;

    /**
     * 拉黑原因
     */
    private String reason;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;

    /**
     * 是否有效
     */
    private Integer isActive;

    @TableLogic
    @TableField("is_deleted")
    private Integer isDeleted;
}
