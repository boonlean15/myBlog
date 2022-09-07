package com.cheney.test.easyexcel.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 导入接收对象
 * @author linch
 * @create 2022/1/13 16:08
 */
@Data
public class ResultExcel {

    @ExcelProperty("file_name")
    private String fileName;
    @ExcelProperty("文件名")
    private String fileNameZh;
    @ExcelProperty("生产厂家")
    private String productFactory;
    @ExcelProperty("设备型号")
    private String deviceModel;
    @ExcelProperty("BBU数量")
    private String bbuNumber;
    @ExcelProperty("板卡名称及数量")
    private String boardNameNumber;
    @ExcelProperty("设备详细安装位置-BBU")
    private String deviceDetailInstallSiteBbu;
    @ExcelProperty("站点编号")
    private String siteNumber;
    @ExcelProperty("所属区县")
    private String belongCounty;
    @ExcelProperty("覆盖类型")
    private String coverType;
    @ExcelProperty("逻辑站名称-文件名")
    private String logicNameFileName;
    @ExcelProperty("经度-RRU")
    private String longitudeRru;
    @ExcelProperty("纬度-RRU")
    private String latitudeRru;
    @ExcelProperty("设备详细安装位置")
    private String deviceDetailInstallSite;
    @ExcelProperty("挂高")
    private String rideHeight;
    @ExcelProperty("方位角")
    private String azimuth;
    @ExcelProperty("机械下倾角")
    private String mechanicalDownTiltAngle;
    @ExcelProperty("电下倾角")
    private String electricDownTiltAngle;
    @ExcelProperty("总下倾角")
    private String totalDownTiltAngle;
    @ExcelProperty("设备型号-RRU")
    private String deviceModelRru;
    @ExcelProperty("设备厂家-AAU")
    private String deviceFactoryAau;
    @ExcelProperty("设备型号-AAU")
    private String deviceModelAau;
    @ExcelProperty("RRU数量")
    private String rruNumber;
    @ExcelProperty("天线厂家")
    private String antennaFactory;
    @ExcelProperty("天线型号")
    private String antennaModel;
    @ExcelProperty("天线数量")
    private String antennaNumber;
    @ExcelProperty("AAU数量")
    private String aauNumber;
    @ExcelProperty("铁塔平台数量")
    private String ironTowerPlatformNumber;
    @ExcelProperty("铁塔塔身高度(米)")
    private String ironTowerTowerBodyHeightMeter;
    @ExcelProperty("铁塔类型")
    private String ironTowerType;
    @ExcelProperty("所在铁塔平台")
    private String belongIronTowerPlatform;
    @ExcelProperty("小区数量")
    private String plotNumber;
    @ExcelProperty("天馈共址基站")
    private String antennaFeederCoLocationBaseSite;
    @ExcelProperty("逻辑站名称-利旧")
    private String logicSiteNameProfitUsed;
    @ExcelProperty("铁塔是否利旧")
    private String ironTowerIsProfitUsed;
    @ExcelProperty("站点经度")
    private String siteLongitude;
    @ExcelProperty("站点纬度")
    private String siteLatitude;
    @ExcelProperty("RRU厂家")
    private String rruFactory;
    @ExcelProperty("载波配置")
    private String carrierProperties;
    @ExcelProperty("载波数")
    private String carrierMath;
    @ExcelProperty("共站情况")
    private String coSiteSituation;
    @ExcelProperty("新规划站点编号")
    private String newPlanSiteNumber;
    @ExcelProperty("逻辑站名称")
    private String logicSiteName;
    @ExcelProperty("下挂基站名称")
    private String downTiltBaseSiteName;
    @ExcelProperty("BBU/CU/DU是否利旧")
    private String bbuCuDuIsProfitUsed;
    @ExcelProperty("BBU/CU/DU利旧/共址逻辑站")
    private String bbuCuDuProfitUsedCoLocationLogicSite;
    @ExcelProperty("BBU/CU/DU设备详细安装位置")
    private String bbuCuDuDeviceDetailInstallSite;
    @ExcelProperty("BBU/CU/DU经度")
    private String bbuCuDuLongitude;
    @ExcelProperty("BBU/CU/DU纬度")
    private String bbuCuDuLatitude;
    @ExcelProperty("BBU/CU/DU机房产权单位")
    private String bbuCuDuMachineRoomEquityUnit;
    @ExcelProperty("RRU/AAU数量")
    private String rruAauNumber;
    @ExcelProperty("RRU/AAU型号")
    private String rruAauModel;
    @ExcelProperty("RRU/AAU是否利旧")
    private String rruAauIsProfitUsed;
    @ExcelProperty("RRU/AAU利旧/共址逻辑站")
    private String rruAauProfitUsedCoLocationLogicSite;
    @ExcelProperty("是否使用AAU")
    private String isUsedAau;
    @ExcelProperty("2_所在铁塔平台")
    private String twoBelongIronTowerPlatform;
    @ExcelProperty("天线设备型号")
    private String antennaDeviceModel;
    @ExcelProperty("是否有美化外罩")
    private String isHadBeautyOuterCover;
    @ExcelProperty("2_铁塔是否利旧")
    private String twoIronTowerIsProfitUsed;
    @ExcelProperty("铁塔产品种类")
    private String ironTowerProductKind;
    @ExcelProperty("2_铁塔类型")
    private String twoIronTowerType;
    @ExcelProperty("铁塔产权单位")
    private String ironTowerEquityUnit;
    @ExcelProperty("铁塔产权性质")
    private String ironTowerEquityCharacter;
    @ExcelProperty("铁塔共享产权")
    private String ironTowerPublicEquity;
    @ExcelProperty("2_铁塔平台数量")
    private String twoIronTowerPlatformNumber;
    @ExcelProperty("2_铁塔塔身高度(米)")
    private String twoIronTowerTowerBodyHeightMeter;
    @ExcelProperty("已使用平台数量")
    private String usedPlatformNumber;
    @ExcelProperty("铁塔高度(米)")
    private String ironTowerHeightMeter;

}
