package com.message.common.domin.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsConfigSelectBo {

    /**
     * 配置 ID
     */
    private Long id;

    /**
     * 配置 ID
     */
    private String configId;

    /**
     * 运营商
     */
    private String supplier;

    /**
     * accessKeyId
     */
    private String accessKeyId;

    /**
     * accessKeySecret
     */
    private String accessKeySecret;

    /**
     * 签名
     */
    private String signature;

    /**
     * 模版 ID
     */
    private String templateId;

    /**
     * bo的创建时间开始
     */
    private Date createdAtStart;

    /**
     * bo的创建时间结束
     */
    private Date createdAtEnd;


    /**
     * bo的创建时间开始
     */
    private Date updatedAtStart;

    /**
     * bo的创建时间结束
     */
    private Date updatedAtEnd;

    private Integer isDeleted;

    /**
     * 当前页数，从1开始
     **/
    private Integer pageNum= 0;
    /**
     * 每页记录数
     **/
    private Integer pageSize=10;
}
