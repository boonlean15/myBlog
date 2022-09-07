package com.cheney.test.easyexcel.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.*;
import com.alibaba.excel.enums.BooleanEnum;
import com.alibaba.excel.enums.poi.BorderStyleEnum;
import com.alibaba.excel.enums.poi.FillPatternTypeEnum;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import lombok.Data;

/**
 * pmis输出表对象
 * @author linch
 * @create 2022/1/17 15:44
 */
@Data
@HeadStyle(fillPatternType= FillPatternTypeEnum.NO_FILL, horizontalAlignment = HorizontalAlignmentEnum.LEFT)
@ContentStyle(fillPatternType= FillPatternTypeEnum.NO_FILL, borderLeft = BorderStyleEnum.THIN,
        borderRight = BorderStyleEnum.THIN, borderTop = BorderStyleEnum.THIN, borderBottom = BorderStyleEnum.THIN)
@HeadFontStyle(fontHeightInPoints = 10, fontName = "微软雅黑", bold = BooleanEnum.FALSE)
@ContentFontStyle(fontHeightInPoints = 10, fontName = "微软雅黑")
public class OutPutPmiExcel {
    @ExcelProperty("规划站名")
    private String planSiteName;
    @ColumnWidth(12)
    @ExcelProperty("规划物理站名")
    private String planPhySiteName;
    @ExcelProperty("经度")
    private String longitude;
    @ExcelProperty("纬度")
    private String latitude;
    @ExcelProperty("地址")
    private String location;
    @ExcelProperty("建设属性")
    private String buildProperty;
    @ExcelProperty("小区数")
    private String plotCount;
    @ExcelProperty("载波配置")
    private String carrierProperties;
    @ExcelProperty("方向角1")
    private String azimuth1;
    @ExcelProperty("方向角2")
    private String azimuth2;
    @ExcelProperty("方向角3")
    private String azimuth3;
    @ExcelProperty("方向角4")
    private String azimuth4;

    @ExcelProperty("下倾角1")
    private String totalDownTiltAngle1;
    @ExcelProperty("下倾角2")
    private String totalDownTiltAngle2;
    @ExcelProperty("下倾角3")
    private String totalDownTiltAngle3;
    @ExcelProperty("下倾角4")
    private String totalDownTiltAngle4;
    @ExcelProperty("挂高1")
    private String rideHeight1;
    @ExcelProperty("挂高2")
    private String rideHeight2;
    @ExcelProperty("挂高3")
    private String rideHeight3;
    @ExcelProperty("挂高4")
    private String rideHeight4;
    @ExcelProperty("项目编号")
    private String projectNumber;


}
