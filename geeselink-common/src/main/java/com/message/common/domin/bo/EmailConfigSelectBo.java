package com.message.common.domin.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailConfigSelectBo {

    private Long id;

    /**
     * 配置 ID
     */
    private String configId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 授权码
     */
    private String password;

    /**
     * host
     */
    private String host;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 协议
     */
    private String protocol;

    /**
     * 默认编码
     */
    private String defaultEncoding;

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
    private Integer pageNum;
    /**
     * 每页记录数
     **/
    private Integer pageSize;
}
