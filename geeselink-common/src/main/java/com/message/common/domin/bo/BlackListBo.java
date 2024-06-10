package com.message.common.domin.bo;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlackListBo {

    /**
     * 消息接收人
     */
    @NotNull
    private String msgTo;

    /**
     * 消息类型
     */
    @NotNull
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
     * 是否有效
     */
    private Integer isActive;
}
