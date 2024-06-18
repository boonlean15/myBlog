package com.cheney.utils.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.data.WriteCellData;
import lombok.Data;

/**
 * @author cheney
 * 日期 2023/7/26
 */
@Data
public class WriteCellDemoData {

    /**
     * 公式
     *
     * @since 3.0.0-beta1
     */
    @ExcelProperty("线路")
    private String line;

    /**
     * 公式
     *
     * @since 3.0.0-beta1
     */
    @ExcelProperty("站点")
    private String site;


}
