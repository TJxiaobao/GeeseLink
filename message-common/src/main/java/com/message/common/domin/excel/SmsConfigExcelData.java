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
public class SmsConfigExcelData {

    @ExcelProperty("ID")
    private Long id;

    /**
     * 配置 ID
     */
    @ExcelProperty("配置 ID")
    private String configId;

    /**
     * 运营商
     */
    @ExcelProperty("运营商")
    private String supplier;

    /**
     * accessKeyId
     */
    @ExcelProperty("accessKeyId")
    private String accessKeyId;

    /**
     * accessKeySecret
     */
    @ExcelProperty("accessKeySecret")
    private String accessKeySecret;

    /**
     * 签名
     */
    @ExcelProperty("签名")
    private String signature;

    /**
     * 模版 ID
     */
    @ExcelProperty("模版 ID")
    private String templateId;


    @ExcelProperty("创建时间")
    private Date createdAt;

    @ExcelProperty("更新时间")
    private Date updatedAt;

    @ExcelProperty("是否删除")
    private Integer isDeleted;
}
