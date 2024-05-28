package com.message.common.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageTypeEnum {

    EMAIL(0, "email", "邮件"),
    SMS(10, "sms", "短信");


    private final int statusCode;

    private final String statusName;

    private final String statusRemark;
}
