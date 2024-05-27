package com.message.common.domin.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ContentRowHeight(10)
@HeadRowHeight(20)
@ColumnWidth(25)
public class EmailConfigExcelData {

    @ExcelProperty("ID")
    private Long id;

    /**
     * 配置 ID
     */
    @ExcelProperty("配置 ID")
    private String configId;

    /**
     * 用户名
     */
    @ExcelProperty("用户名")
    private String username;

    /**
     * 授权码
     */
    @ExcelProperty("授权码")
    private String password;

    /**
     * host
     */
    @ExcelProperty("host")
    private String host;

    /**
     * 端口
     */
    @ExcelProperty("端口")
    private Integer port;

    /**
     * 协议
     */
    @ExcelProperty("协议")
    private String protocol;

    /**
     * 默认编码
     */
    @ExcelProperty("默认编码")
    private String defaultEncoding;

    @ExcelProperty("创建时间")
    private Date createdAt;

    @ExcelProperty("更新时间")
    private Date updatedAt;

    @ExcelProperty("是否删除")
    private Integer isDeleted;
}