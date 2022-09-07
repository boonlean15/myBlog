package com.cheney.test.easyexcel.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.*;
import com.alibaba.excel.enums.BooleanEnum;
import com.alibaba.excel.enums.poi.BorderStyleEnum;
import com.alibaba.excel.enums.poi.FillPatternTypeEnum;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import lombok.Data;

/**
 * 导出设置对象
 * @author linch
 * @create 2022/1/14 11:18
 */
@Data
@HeadStyle(fillPatternType= FillPatternTypeEnum.NO_FILL, horizontalAlignment = HorizontalAlignmentEnum.LEFT)
@ContentStyle(fillPatternType= FillPatternTypeEnum.NO_FILL, borderLeft = BorderStyleEnum.THIN,
        borderRight = BorderStyleEnum.THIN, borderTop = BorderStyleEnum.THIN, borderBottom = BorderStyleEnum.THIN)
@HeadFontStyle(fontHeightInPoints = 10, fontName = "微软雅黑", bold = BooleanEnum.FALSE)
@ContentFontStyle(fontHeightInPoints = 10, fontName = "微软雅黑")
public class OutPutExcel {

    @ExcelProperty("序号")
    private String serialNumber;
    @ExcelProperty("对象")
    private String instance;
    @ColumnWidth(12)
    @ExcelProperty("属性中文名称")
    private String propertyNameZh;
    @ColumnWidth(20)
    @ExcelProperty("规则使用属性中文名称")
    private String rulesUseAttributeNameZh;
    @ExcelProperty("数据来源")
    private String dataSource;
    @ExcelProperty("跟进人")
    private String forwardPeople;
    @ColumnWidth(22)
    @ExcelProperty("样例（新增数据填写本列）")
    private String template;

    @ExcelProperty("备注")
    private String remark;

}
