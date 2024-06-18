package com.cheney.utils.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author cheney
 * 日期 2023/7/26
 */
@Data
public class SiteExcel {
    @ExcelProperty("标题1")
    private String one;
    @ExcelProperty("标题2")
    private String two;
}
