package com.message.common.domin.bo;

import lombok.Data;


@Data
public class EmailInfoBo {
    private String subject;

    private String content;

    private String[] to;

    private String from;

}
