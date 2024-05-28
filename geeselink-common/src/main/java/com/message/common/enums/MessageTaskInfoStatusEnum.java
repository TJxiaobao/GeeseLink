package com.message.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageTaskInfoStatusEnum {

    // 任务状态 0 未发送, 1 发送中, 2 发送成功, 3 发送失败

    STATUS_ENUM_NO_SEND(0, "no_send", "未发送"),

    STATUS_ENUM_SENDING(1, "sending", "发送中"),

    STATUS_ENUM_SEND_SUCCESS(2, "send_success", "发送成功"),

    STATUS_ENUM_SEND_FAIL(3, "send_fail", "发送失败");

    private final int statusCode;

    private final String statusName;

    private final String statusRemark;
}
