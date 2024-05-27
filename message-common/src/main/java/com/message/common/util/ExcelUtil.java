package com.message.common.util;

import com.alibaba.excel.EasyExcel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExcelUtil {

    public static void writeExcel(String path, Class<?> tClass, String sheetName, List<?> data) {

        String fileName = path + "exportExcel" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, tClass).sheet(sheetName == null ? "模板" : sheetName).doWrite(data);
    }
}
