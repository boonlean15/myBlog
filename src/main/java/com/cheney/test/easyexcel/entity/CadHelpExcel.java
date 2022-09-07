package com.cheney.test.easyexcel.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * excel导入和导出辅助类
 * @author linch
 * @create 2022/1/14 10:21
 */
@Data
public class CadHelpExcel {

    /**
     * 导入接收表头
     */
    private List<String> heads;

    ////室分新址
    /**
     * 室分新址-序号集合
     */
    private List<String> inlSerialNumbers = new ArrayList<>();

    private int inlSerialNumber = 44;
    public List<String> getInlSerialNumbers() {
        inlSerialNumbers.clear();
        for(int i = 1; i <= inlSerialNumber; i++ ){
            inlSerialNumbers.add("" + i);
        }
        return inlSerialNumbers;
    }
    /**
     * 室分新址-对象
     */
    private List<String> inlInstances = new ArrayList<String>(){{
        add("逻辑站");add("逻辑站");add("逻辑站");add("逻辑站");add("逻辑站");add("小区");
        add("小区");add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");
        add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");
        add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");add("RRU/AAU");
        add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");
        add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");
        add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");add("板卡");add("板卡");add("分布系统");
        add("分布系统");add("填写时间");}};
    /**
     * 室分新址-属性中文名称
     */
    private List<String> inlPropertyNameZhs = new ArrayList<String>(){{
        add("新规划站点编号");add("逻辑站名称");add("下挂基站");add("CRAN机房规划名称");add("CRAN综资机房名称");add("站点配置");
        add("是否拉远小区");add("综资机房名称");add("设备详细安装位置");add("经度");add("纬度");add("关联铁塔公司站址编码");
        add("是否利旧");add("利旧/共址逻辑站");add("设备厂家");add("数量");add("型号");add("机房共享单位");
        add("机房产权单位");add("机房配套共享单位");add("机房外电共享单位");add("机房面积");add("机房产品分类");add("综资机房名称");
        add("设备详细安装位置");add("经度");add("纬度");add("关联铁塔公司站址编码");add("数量");add("型号");
        add("是否利旧");add("利旧/共址逻辑站");add("机房共享单位");add("机房产权单位");add("机房配套共享单位");add("机房外电共享单位");
        add("机房面积");add("机房产品分类");add("是否使用AAU");add("板卡名称");add("设备型号");add("楼栋数量");
        add("天线数");add("填写时间");
    }};
    /**
     * 室分新址-规则使用属性中文名称
     */
    private List<String> inlRulesUseAttributeNameZhs = new ArrayList<String>(){{
        add("新规划站点编号");add("逻辑站名称");add("下挂基站");add("CRAN机房规划名称");add("CRAN综资机房名称");add("站点配置");
        add("是否拉远小区");add("BBU/CU/DU综资机房名称");add("BBU/CU/DU设备详细安装位置");add("BBU/CU/DU经度");add("BBU/CU/DU纬度");
        add("BBU/CU/DU关联铁塔公司站址编码");add("BBU/CU/DU是否利旧");add("BBU/CU/DU利旧/共址逻辑站");add("BBU/CU/DU设备厂家");
        add("BBU/CU/DU数量");add("BBU/CU/DU型号");add("BBU/CU/DU机房共享单位");add("BBU/CU/DU机房产权单位");
        add("BBU/CU/DU机房配套共享单位");add("BBU/CU/DU机房外电共享单位");add("BBU/CU/DU机房面积");add("BBU/CU/DU机房产品分类");
        add("RRU/AAU综资机房名称");add("RRU/AAU设备详细安装位置");add("RRU/AAU经度");add("RRU/AAU纬度");
        add("RRU/AAU关联铁塔公司站址编码");add("RRU/AAU数量");add("RRU/AAU型号");add("RRU/AAU是否利旧");
        add("RRU/AAU利旧/共址逻辑站");add("RRU/AAU机房共享单位");add("RRU/AAU机房产权单位");add("RRU/AAU机房配套共享单位");
        add("RRU/AAU机房外电共享单位");add("RRU/AAU机房面积");add("RRU/AAU机房产品分类");add("是否使用AAU");
        add("板卡名称");add("板卡设备型号");add("分布系统楼栋数量");add("分布系统每栋天线数");add("填写时间");
    }};
    /**
     * 室分新址-数据来源
     */
    private List<String> inlDataSources = new ArrayList<String>(){{
        add("图纸");add("图纸");add("图纸");add("图纸");add("图纸");add("图纸");
        add("图纸");add("图纸");add("图纸");add("图纸");add("图纸");add("图纸");
        add("图纸");add("图纸");add("图纸");add("图纸");add("图纸");add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");
        add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");
        add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");
        add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");add("图纸");
        add("图纸");add("图纸");add("图纸");add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");add("图纸");add("图纸");
        add("图纸");add("图纸");add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");
        add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");
        add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");
        add("图纸");add("图纸");add("图纸");add("图纸");
        add("图纸");add("图纸");
    }};
    /**
     * 室分新址-跟进人
     */
    private List<String> inlForwardPeoples = new ArrayList<>();

    public List<String> getInlForwardPeoples() {
        for(int i = 1; i <= inlSerialNumber; i++ ){
            inlForwardPeoples.add("设计院");
        }
        return inlForwardPeoples;
    }

    /**
     * 室分新址-样例（新增数据填写本列）
     */
    private List<String> inlTemplates = new ArrayList<>();

    public List<String> getInlTemplates() {
        inlTemplates.clear();
        for(int i = 1; i <= inlSerialNumber; i++ ){
            inlTemplates.add(null);
        }
        return inlTemplates;
    }

    /**
     * 室分新址-备注
     */
    private List<String> inlRemarks = new ArrayList<String>(){{
        add(null);add(null);add(null);add(null);add(null);add("以\"/\"间割，室外站点以S开头，如：S1/1/1。室分站点以o开头，如o1/1/1。如果是硬扩：【站点配置】体现新旧配置，例子：原S1/1/1，新S2/2/2/2，填写如下：S1/1/1-S2/1/2/2，o1/1/1/-o2/2/2。如果部分小区不开，填写0，如只开方向4/5小区：0/0/0/1/1。");
        add(null);add("填写综资系统机房名称，共址机房找无优徐恒辉提供数据");add(null);add("经纬度需保留小数点后五位");add("经纬度需保留小数点后五位");add("铁塔机房必填，获取来源接口待确认");
        add(null);add("如果是利旧BBU，填写所利旧逻辑站名；如果BBU共址，填写共址逻辑站名称（优先填写在用4G站点）");add("中兴、爱立信、华为");add("如与其他系统共BBU，数量填0");add(null);add("枚举值：电信、电信+联通、联通、无");
        add("枚举值：第三方、其它、业主、中国广电、中国铁塔、中国铁通、中国移动");add("枚举值：电信、电信+联通、联通、无");add("枚举值：电信、电信+联通、联通、无");add("单位为（平方米），一体化机柜按实际数量，挂墙等非标机房填1");add("枚举值：RRU拉远、彩钢板、框架、其他、无机房、一体化机房、一体化机柜、移动自维、砖混、租用");add("共址机房找无优徐恒辉提供数据");
        add("统一按大数据规划天面地址填写");add("经纬度需保留小数点后五位，统一按大数据规划经纬度填写");add("经纬度需保留小数点后五位，统一按大数据规划经纬度填写");add("铁塔机房必填，获取来源接口待确认");add("以\"/\"间割，【RRU/AAU数量】与【站点配置】格式保持一致。如与其他系统共RRU/AAU，数量填0");add("先按设计输出填写，施工领货与设计部符时后期让施工核对修正");
        add(null);add("如果是利旧AAU/RRU，填写所利旧逻辑站名；如果AAU/RRU共址，填写共址逻辑站名称（优先填写在用4G站点）");add("枚举值：电信、电信+联通、联通、无");add("枚举值：第三方、其它、业主、中国广电、中国铁塔、中国铁通、中国移动");add("枚举值：电信、电信+联通、联通、无");add("枚举值：电信、电信+联通、联通、无");
        add("单位为（平方米），如安装在天面直接填1");add("枚举值：RRU拉远、彩钢板、框架、其他、无机房、一体化机房、一体化机柜、移动自维、砖混、租用");add(null);add("多个板卡以“/”为间隔");add("多个板卡以“/”为间隔");add("裙楼、附楼等（按单楼计数）、地下车库不算楼栋数，楼栋填总数");
        add("地下车库（天线平均到楼栋数），天线数按2A/3B/5C/2D（其中数字是楼栋数量，A天线数在＜15、B天线数在＞15≤100、C天线数在大于100≤300、D天线数在300以上）");add("设计方案大表填写时间，不能带公式");
    }};

    /**
     * 室分新址后端输入字段map
     */
    private Map<String, String> inlTemplateMap = new HashMap<String, String>(){{
        put("1","newPlanSiteNumber");
        put("2","logicSiteName");
        put("3","downTiltBaseSiteName");
        put("6","carrierProperties");
        put("9","bbuCuDuDeviceDetailInstallSite");
        put("10","bbuCuDuLongitude");
        put("11","bbuCuDuLatitude");
        put("13","rruAauIsProfitUsed");
        put("14","rruAauProfitUsedCoLocationLogicSite");
        put("15","productFactory");
        put("16","bbuNumber");
        put("17","deviceModel");
//        put("25","deviceDetailInstallSite");
        put("26","longitudeRru");
        put("27","latitudeRru");
        put("29","rruAauNumber");
        put("30","rruAauModel");
        put("31","rruAauIsProfitUsed");
        put("32","rruAauProfitUsedCoLocationLogicSite");
        put("39","isUsedAau");
        put("40","boardNameNumber");
        put("41","boardNameNumber");
    }};



    ///室分共址
    /**
     * 室分共址-序号集合
     */
    private List<String> iclSerialNumbers = new ArrayList<>();
    private int iclSerialNumber = 44;

    public List<String> getIclSerialNumbers() {
        iclSerialNumbers.clear();
        for(int i = 1; i <= iclSerialNumber; i++ ){
            iclSerialNumbers.add("" + i);
        }
        return iclSerialNumbers;
    }

    /**
     * 室分共址-对象集合
     */
    private List<String> iclInstances = new ArrayList<String>(){{
        add("逻辑站");add("逻辑站");add("逻辑站");add("逻辑站");add("逻辑站");add("小区");
        add("小区");add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");
        add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");
        add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");add("RRU/AAU");
        add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");
        add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");
        add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");add("板卡");add("板卡");add("分布系统");
        add("分布系统");add("填写时间");
    }};
    /**
     * 室分共址-属性中文名称
     */
    private List<String> iclPropertyNameZhs = new ArrayList<String>(){{
        add("新规划站点编号");add("逻辑站名称");add("下挂基站");add("CRAN机房规划名称");add("CRAN综资机房名称");add("站点配置");
        add("是否拉远小区");add("综资机房名称");add("设备详细安装位置");add("经度");add("纬度");add("关联铁塔公司站址编码");
        add("是否利旧");add("利旧/共址逻辑站");add("设备厂家");add("数量");add("型号");add("机房共享单位");
        add("机房产权单位");add("机房配套共享单位");add("机房外电共享单位");add("机房面积");add("机房产品分类");add("综资机房名称");
        add("设备详细安装位置");add("经度");add("纬度");add("关联铁塔公司站址编码");add("利旧/共址逻辑站");add("数量");
        add("型号");add("是否利旧");add("机房共享单位");add("机房产权单位");add("机房配套共享单位");add("机房外电共享单位");
        add("机房面积");add("机房产品分类");add("是否使用AAU");add("板卡名称");add("设备型号");add("楼栋数量");
        add("天线数");add("填写时间");
    }};
    /**
     * 室分共址-规则使用属性中文名称
     */
    private List<String> iclRulesUseAttributeNameZhs = new ArrayList<String>(){{
        add("新规划站点编号");add("逻辑站名称");add("下挂基站");add("CRAN机房规划名称");add("CRAN综资机房名称");add("站点配置");
        add("是否拉远小区");add("BBU/CU/DU综资机房名称");add("BBU/CU/DU设备详细安装位置");add("BBU/CU/DU经度");add("BBU/CU/DU纬度");
        add("BBU/CU/DU关联铁塔公司站址编码");add("BBU/CU/DU是否利旧");add("BBU/CU/DU利旧/共址逻辑站");add("BBU/CU/DU设备厂家");
        add("BBU/CU/DU数量");add("BBU/CU/DU型号");add("BBU/CU/DU机房共享单位");add("BBU/CU/DU机房产权单位");
        add("BBU/CU/DU机房配套共享单位");add("BBU/CU/DU机房外电共享单位");add("BBU/CU/DU机房面积");add("BBU/CU/DU机房产品分类");
        add("RRU/AAU综资机房名称");add("RRU/AAU设备详细安装位置");add("RRU/AAU经度");add("RRU/AAU纬度");
        add("RRU/AAU关联铁塔公司站址编码");add("RRU/AAU利旧/共址逻辑站");add("RRU/AAU数量");add("RRU/AAU型号");add("RRU/AAU是否利旧");
        add("RRU/AAU机房共享单位");add("RRU/AAU机房产权单位");add("RRU/AAU机房配套共享单位");
        add("RRU/AAU机房外电共享单位");add("RRU/AAU机房面积");add("RRU/AAU机房产品分类");add("是否使用AAU");
        add("板卡名称");add("板卡设备型号");add("分布系统楼栋数量");add("分布系统每栋天线数");add("填写时间");
    }};
    /**
     * 室分共址-数据来源
     */
    private List<String> iclDataSources = new ArrayList<String>(){{
        add("图纸");add("图纸");add("图纸");add("图纸");add("图纸");add("图纸");
        add("图纸");add("图纸");add("图纸");add("图纸");add("图纸");add("图纸");
        add("图纸");add("图纸");add("图纸");add("图纸");add("图纸");add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");
        add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");
        add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");
        add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");add("图纸");
        add("图纸");add("图纸");add("图纸");add("网管中心直接从全生命系统、综资提取");add("图纸");add("图纸");
        add("图纸");add("图纸");add("网管中心直接从全生命系统、综资提取");add("网管中心直接从全生命系统、综资提取");
        add("网管中心直接从全生命系统、综资提取");add("网管中心直接从全生命系统、综资提取");
        add("网管中心直接从全生命系统、综资提取");add("网管中心直接从全生命系统、综资提取");
        add("图纸");add("图纸");add("图纸");add("网管中心直接从全生命系统、综资提取");
        add("网管中心直接从全生命系统、综资提取");add("图纸");
    }};
    /**
     * 室分共址-跟进人
     */
    private List<String> iclForwardPeoples = new ArrayList<String>(){{
        add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");
        add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");
        add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");
        add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");
        add("设计院");add("设计院");add("设计院");
        add("无忧/徐恒辉");
        add("设计院");add("设计院");add("设计院");add("设计院");
        add("无忧/徐恒辉");add("无忧/徐恒辉");add("无忧/徐恒辉");add("无忧/徐恒辉");add("无忧/徐恒辉");add("无忧/徐恒辉");
        add("设计院");add("设计院");add("设计院");add("无忧/徐恒辉");
        add("无忧/徐恒辉");add("设计院");
    }};
    /**
     * 室分共址-样例（新增数据填写本列）
     */
    private List<String> iclTemplates = new ArrayList<>();

    public List<String> getIclTemplates() {
        iclTemplates.clear();
        for(int i = 1; i <= iclSerialNumber; i++){
            iclTemplates.add(null);
        }
        return iclTemplates;
    }

    /**
     * 室分共址-备注
     */
    private List<String> iclRemarks = new ArrayList<String>(){{
        add(null);add(null);add(null);add(null);add(null);add("以\"/\"间割，室外站点以S开头，如：S1/1/1。室分站点以o开头，如o1/1/1。如果是硬扩：【站点配置】体现新旧配置，例子：原S1/1/1，新S2/2/2/2，填写如下：S1/1/1-S2/1/2/2，o1/1/1/-o2/2/2。如果部分小区不开，填写0，如只开方向4/5小区：0/0/0/1/1。");
        add(null);add("共址机房找无优徐恒辉提供数据");add(null);add("经纬度需保留小数点后五位");add("经纬度需保留小数点后五位");add("铁塔机房必填，获取来源接口待确认");
        add(null);add("如果是利旧BBU，填写所利旧逻辑站名；如果BBU共址，填写共址逻辑站名称（优先填写在用4G站点）");add("中兴、爱立信、华为");add("如与其他系统共BBU，数量填0");add(null);add("枚举值：电信、电信+联通、联通、无");
        add("枚举值：第三方、其它、业主、中国广电、中国铁塔、中国铁通、中国移动");add("枚举值：电信、电信+联通、联通、无");add("枚举值：电信、电信+联通、联通、无");add("单位为（平方米），一体化机柜按实际数量，挂墙等非标机房填1");add("枚举值：RRU拉远、彩钢板、框架、其他、无机房、一体化机房、一体化机柜、移动自维、砖混、租用");add("共址机房找无优徐恒辉提供数据");
        add("统一按大数据规划天面地址填写");add("经纬度需保留小数点后五位，统一按大数据规划经纬度填写");add("经纬度需保留小数点后五位，统一按大数据规划经纬度填写");add("铁塔机房必填，获取来源接口待确认");add("如果是利旧AAU/RRU，填写所利旧逻辑站名；如果AAU/RRU共址，填写共址逻辑站名称（优先填写在用4G站点）");add("以\"/\"间割，【RRU/AAU数量】与【站点配置】格式保持一致。如与其他系统共RRU/AAU，数量填0");
        add("先按设计输出填写，施工领货与设计部符时后期让施工核对修正");add(null);add("枚举值：电信、电信+联通、联通、无");add("枚举值：第三方、其它、业主、中国广电、中国铁塔、中国铁通、中国移动");add("枚举值：电信、电信+联通、联通、无");add("枚举值：电信、电信+联通、联通、无");
        add("单位为（平方米），如安装在天面直接填1");add("枚举值：RRU拉远、彩钢板、框架、其他、无机房、一体化机房、一体化机柜、移动自维、砖混、租用");add(null);add("多个板卡以“/”为间隔");add("多个板卡以“/”为间隔");add("裙楼、附楼等（按单楼计数）、地下车库不算楼栋数，楼栋填总数");
        add("地下车库（天线平均到楼栋数），天线数按2A/3B/5C/2D（其中数字是楼栋数量，A天线数在＜15、B天线数在＞15≤100、C天线数在大于100≤300、D天线数在300以上）");add("设计方案大表填写时间，不能带公式");

    }};

    /**
     * 室分共址后端输入字段map
     */
    private Map<String, String> iclTemplateMap = new HashMap<String, String>(){{
        put("1","newPlanSiteNumber");
        put("2","logicSiteName");
        put("3","downTiltBaseSiteName");
        put("6","carrierProperties");
        put("9","bbuCuDuDeviceDetailInstallSite");
        put("10","bbuCuDuLongitude");
        put("11","bbuCuDuLatitude");
        put("13","rruAauIsProfitUsed");
        put("14","rruAauProfitUsedCoLocationLogicSite");
        put("15","productFactory");
        put("16","bbuNumber");
        put("17","deviceModel");
//        put("25","deviceDetailInstallSite");
        put("26","longitudeRru");
        put("27","latitudeRru");
        put("29","rruAauNumber");
        put("30","rruAauModel");
        put("31","rruAauIsProfitUsed");
        put("32","rruAauProfitUsedCoLocationLogicSite");
        put("39","isUsedAau");
        put("40","boardNameNumber");
        put("41","boardNameNumber");
    }};


    ///室外新址
    /**
     * 室外新址-序号集合
     */
    private List<String> onlSerialNumbers = new ArrayList<>();
    private int onlSerialNumber = 63;

    public List<String> getOnlSerialNumbers() {
        onlSerialNumbers.clear();
        for(int i = 1; i <= onlSerialNumber; i++ ){
            onlSerialNumbers.add("" + i);
        }
        return onlSerialNumbers;
    }

    /**
     * 室外新址-对象集合
     */
    private List<String> onlInstances = new ArrayList<String>(){{
        add("逻辑站");add("逻辑站");add("逻辑站");add("逻辑站");add("逻辑站");add("小区");add("小区");add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");
        add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");
        add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");
        add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");add("天线");
        add("天线");add("天线");add("天线");add("天线");add("天线");add("天线");add("天线");add("天线");add("天线");add("天线");
        add("铁塔");add("铁塔");add("铁塔");add("铁塔");add("铁塔");add("铁塔");add("铁塔");add("铁塔");add("铁塔");add("铁塔");
        add("板卡");add("板卡");add("填写时间");
    }};
    /**
     * 室外新址-属性中文名称
     */
    private List<String> onlPropertyNameZhs = new ArrayList<String>(){{
        add("新规划站点编号");add("逻辑站名称");add("下挂基站");add("CRAN机房规划名称");add("CRAN综资机房名称");add("站点配置");add("是否拉远小区");add("综资机房名称");add("设备详细安装位置");add("经度");
        add("纬度");add("关联铁塔公司站址编码");add("是否利旧");add("利旧/共址逻辑站");add("设备厂家");add("数量");add("型号");add("机房共享单位");add("机房产权单位");add("机房配套共享单位");
        add("机房外电共享单位");add("机房面积");add("机房产品分类");add("综资机房名称");add("设备详细安装位置");add("经度");add("纬度");add("关联铁塔公司站址编码");add("数量");add("型号");
        add("是否利旧");add("利旧/共址逻辑站");add("机房共享单位");add("机房产权单位");add("机房配套共享单位");add("机房外电共享单位");add("机房面积");add("机房产品分类");add("是否使用AAU");add("设备详细安装位置");
        add("方位角");add("机械下倾角");add("挂高");add("电下倾角");add("所在铁塔平台");add("设备型号");add("是否有美化外罩");add("天线类型");add("是否利旧");add("利旧天线频段");
        add("是否利旧");add("铁塔产品种类");add("铁塔类型");add("铁塔产权单位");add("铁塔产权性质");add("铁塔共享产权");add("铁塔平台数量");add("铁塔塔身高度(米)");add("已使用平台数量");add("铁塔高度(米)");
        add("板卡名称");add("设备型号");add("填写时间");
    }};
    /**
     * 室外新址-规则使用属性中文名称
     */
    private List<String> onlRulesUseAttributeNameZhs = new ArrayList<String>(){{
        add("新规划站点编号");add("逻辑站名称");add("下挂基站名称");add("CRAN机房规划名称");add("CRAN综资机房名称");add("站点配置");add("是否拉远小区");add("BBU/CU/DU综资机房名称");add("BBU/CU/DU设备详细安装位置");add("BBU/CU/DU经度");
        add("BBU/CU/DU纬度");add("BBU/CU/DU关联铁塔公司站址编码");add("BBU/CU/DU是否利旧");add("BBU/CU/DU利旧/共址逻辑站");add("BBU/CU/DU设备厂家");add("BBU/CU/DU数量");add("BBU/CU/DU型号");add("BBU/CU/DU机房共享单位");add("BBU/CU/DU机房产权单位");add("BBU/CU/DU机房配套共享单位");
        add("BBU/CU/DU机房外电共享单位");add("BBU/CU/DU机房面积");add("BBU/CU/DU机房产品分类");add("RRU/AAU综资机房名称");add("RRU/AAU设备详细安装位置");add("RRU/AAU经度");add("RRU/AAU纬度");add("RRU/AAU关联铁塔公司站址编码");add("RRU/AAU数量");add("RRU/AAU型号");
        add("RRU/AAU是否利旧");add("RRU/AAU利旧/共址逻辑站");add("RRU/AAU机房共享单位");add("RRU/AAU机房产权单位");add("RRU/AAU机房配套共享单位");add("RRU/AAU机房外电共享单位");add("RRU/AAU机房面积");add("RRU/AAU机房产品分类");add("是否使用AAU");add("天线设备详细安装位置");
        add("方位角");add("机械下倾角");add("挂高");add("电下倾角");add("所在铁塔平台");add("天线设备型号");add("是否有美化外罩");add("天线类型");add("天线是否利旧");add("利旧天线频段");
        add("铁塔是否利旧");add("铁塔产品种类");add("铁塔类型");add("铁塔产权单位");add("铁塔产权性质");add("铁塔共享产权");add("铁塔平台数量");add("铁塔塔身高度(米)");add("已使用平台数量");add("铁塔高度(米)");
        add("板卡名称");add("板卡设备型号");add("填写时间");
    }};
    /**
     * 室外新址-数据来源
     */
    private List<String> onlDataSources = new ArrayList<String>(){{
        add("图纸");add("图纸");add("图纸");add("图纸");add("图纸");add("图纸");add("图纸");add("图纸");add("图纸");add("图纸");
        add("图纸");add("图纸");add("图纸");add("图纸");add("图纸");add("图纸");add("图纸");add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");
        add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");add("图纸");add("图纸");add("图纸");add("图纸");add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");add("图纸");add("图纸");
        add("图纸");add("图纸");add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");add("图纸");add("图纸");
        add("图纸");add("图纸");add("图纸");add("图纸");add("图纸");add("图纸");add("图纸");add("图纸");add("图纸");add("图纸");
        add("图纸");add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");add("图纸，信息与勘察设计拍照时间为准，后续如发生变化由维护负责");
        add("图纸");add("图纸");add("图纸");
    }};
    /**
     * 室外新址-跟进人
     */
    private List<String> onlForwardPeoples = new ArrayList<String>(){{
        add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");
        add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");
        add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");
        add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");
        add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");
        add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");
        add("设计院");add("设计院");add("设计院");
    }};
    /**
     * 室外新址-样例（新增数据填写本列）
     */
    private List<String> onlTemplates = new ArrayList<>();

    public List<String> getOnlTemplates() {
        onlTemplates.clear();
        for(int i = 1; i <= onlSerialNumber; i++ ){
            onlTemplates.add(null);
        }
        return onlTemplates;
    }

    /**
     * 室外新址-备注
     */
    private List<String> onlRemarks = new ArrayList<String>(){{
        add(null);add(null);add(null);add(null);add(null);add("以\"/\"间割，室外站点以S开头，如：S1/1/1。室分站点以o开头，如o1/1/1。如果是硬扩：【站点配置】体现新旧配置，例子：原S1/1/1，新S2/2/2/2，填写如下：S1/1/1-S2/1/2/2，o1/1/1/-o2/2/2。如果部分小区不开，填写0，如只开方向4/5小区：0/0/0/1/1。");add(null);add("共址机房找无优徐恒辉提供数据");add(null);add("经纬度需保留小数点后五位");
        add("经纬度需保留小数点后五位");add("铁塔机房必填，获取来源接口待确认");add(null);add("如果是利旧BBU，填写所利旧逻辑站名；如果BBU共址，填写共址逻辑站名称（优先填写在用4G站点）");add("中兴、爱立信、华为");add("如与其他系统共BBU，数量填0");add(null);add("枚举值：电信、电信+联通、联通、无");add("枚举值：第三方、其它、业主、中国广电、中国铁塔、中国铁通、中国移动");add("枚举值：电信、电信+联通、联通、无");
        add("枚举值：电信、电信+联通、联通、无");add("单位为（平方米），一体化机柜按实际数量，挂墙等非标机房填1");add("枚举值：RRU拉远、彩钢板、框架、其他、无机房、一体化机房、一体化机柜、移动自维、砖混、租用");add("共址机房找无优徐恒辉提供数据");add("统一按大数据规划天面地址填写");add("经纬度需保留小数点后五位，统一按大数据规划经纬度填写");add("经纬度需保留小数点后五位，统一按大数据规划经纬度填写");add("铁塔机房必填，获取来源接口待确认");add("以\"/\"间割，【RRU/AAU数量】与【站点配置】格式保持一致。如与其他系统共RRU/AAU，数量填0");add("先按设计输出填写，施工领货与设计部符时后期让施工核对修正");
        add(null);add("如果是利旧AAU/RRU，填写所利旧逻辑站名；如果AAU/RRU共址，填写共址逻辑站名称（优先填写在用4G站点）");add("枚举值：电信、电信+联通、联通、无");add("枚举值：第三方、其它、业主、中国广电、中国铁塔、中国铁通、中国移动");add("枚举值：电信、电信+联通、联通、无");add("枚举值：电信、电信+联通、联通、无");add("单位为（平方米），如安装在天面直接填1");add("枚举值：RRU拉远、彩钢板、框架、其他、无机房、一体化机房、一体化机柜、移动自维、砖混、租用");add(null);add(null);
        add("以“/”为间隔");add("以“/”为间隔");add("以“/”为间隔");add("以“/”为间隔");add("以“/”为间隔");add("与AAU一体化需填AAU型号，先按天线类型填写后期由施工修正");add(null);add(null);add(null);add("如果是利旧天线，以“/”为间隔，与【站点配置】格式保持一致。每个小区只填写一个利旧的天线频段，如果其中一个天线是新增则为空。如：GS//DC，则表示1和3小区是利旧GS和DC，2小区是新增天线。");
        add(null);add("（多种铁塔产品种类时以“/”来增加）枚举值：自维铁塔、普通地面塔、景观塔、简易塔、普通楼面塔、楼面抱杆");add("（多种铁塔产品种类时以“/”来增加）枚举值：18米等迳杆,H杆,抱杆,壁挂,便携式一体化箱体,地面H杆,地面灯塔,地面拉线塔,地面自立塔,房顶四角塔,仿生树,杆形塔,钢管,钢管塔,广告牌,集束天线,监控杆,空调美化,空调型,立杆,楼顶单管塔,楼顶角钢塔,楼顶井字架,楼顶景观塔,楼顶拉线塔,楼顶美化天线,楼顶三管塔,楼顶围杆塔,落地角钢塔,落地景观塔,落地拉线塔,落地内爬单管塔,落地三管塔,落地外爬单管塔,美化桶,排气管美化,升降塔,水泥杆,围杆塔,屋面立杆,屋面围杆塔,屋面自立塔,烟囱美化,增高架");add("枚举值：中国铁塔、中国移动、中国联通、中国电信、中国铁通、中国广电、业主、其他、第三方");add("枚举值：自有（自建）、自有（合建）、自有（购买）、租用、用户所有");add("枚举值：电信,电信+联通,联通,铁塔,北讯，电信+联通+北讯，电信+北讯，联通+北讯，铁塔+北讯，无");add(null);add(null);add("平台数量以实际数量计算，包括共建共享单位使用的平台，抱杆没有平台填1");add(null);
        add("多个板卡以“/”为间隔");add("多个板卡以“/”为间隔");add("设计方案大表填写时间，不能带公式");
    }};
    /**
     * 室外新址后端输入字段map
     */
    private Map<String, String> onlTemplateMap = new HashMap<String, String>(){{
        put("1","newPlanSiteNumber");
        put("2","logicSiteName");
        put("3","downTiltBaseSiteName");
        put("6","carrierProperties");
        put("9","bbuCuDuDeviceDetailInstallSite");//BBU/CU/DU设备详细安装位置
        put("10","bbuCuDuLongitude");//BBU/CU/DU经度

        put("11","bbuCuDuLatitude");//BBU/CU/DU纬度
        put("13","bbuCuDuIsProfitUsed");
        put("14","bbuCuDuProfitUsedCoLocationLogicSite");
        put("15","productFactory");
        put("16","bbuNumber");
        put("17","deviceModel");

//        put("25","deviceDetailInstallSite");
        put("26","longitudeRru");
        put("27","latitudeRru");
        put("29","rruAauNumber");
        put("30","rruAauModel");

        put("31","rruAauIsProfitUsed");
        put("32","rruAauProfitUsedCoLocationLogicSite");
        put("39","isUsedAau");
        put("40","deviceDetailInstallSite");

        put("41", "azimuth");
        put("42", "mechanicalDownTiltAngle");
        put("43", "rideHeight");

        put("44", "electricDownTiltAngle");
        put("45", "twoBelongIronTowerPlatform");
        put("46", "antennaDeviceModel");
        put("47", "isHadBeautyOuterCover");
        put("49", "antennaNumber");
        put("51", "twoIronTowerIsProfitUsed");
        put("52", "ironTowerProductKind");
        put("53", "twoIronTowerType");
        put("54", "ironTowerEquityUnit");

        put("55", "ironTowerEquityCharacter");
        put("56", "ironTowerPublicEquity");
        put("57", "twoIronTowerPlatformNumber");
        put("58", "twoIronTowerTowerBodyHeightMeter");
        put("59", "usedPlatformNumber");
        put("60", "ironTowerHeightMeter");
        put("61","boardNameNumber");
        put("62","boardNameNumber");

    }};


    ///室外共址
    /**
     * 室外共址-序号集合
     */
    private List<String> oclSerialNumbers = new ArrayList<>();
    private int oclSerialNumber = 63;

    public List<String> getOclSerialNumbers() {
        oclSerialNumbers.clear();
        for(int i = 1; i <= oclSerialNumber; i++ ){
            oclSerialNumbers.add("" + i);
        }
        return oclSerialNumbers;
    }
    /**
     * 室外共址-对象集合
     */
    private List<String> oclInstances = new ArrayList<String>(){{
        add("逻辑站");add("逻辑站");add("逻辑站");add("逻辑站");add("逻辑站");add("小区");add("小区");add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");
        add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");
        add("BBU/CU/DU");add("BBU/CU/DU");add("BBU/CU/DU");add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");
        add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");add("RRU/AAU");add("天线");
        add("天线");add("天线");add("天线");add("天线");add("天线");add("天线");add("天线");add("天线");add("天线");add("天线");
        add("铁塔");add("铁塔");add("铁塔");add("铁塔");add("铁塔");add("铁塔");add("铁塔");add("铁塔");add("铁塔");add("铁塔");
        add("板卡");add("板卡");add("填写时间");
    }};
    /**
     * 室外共址-属性中文名称
     */
    private List<String> oclPropertyNameZhs = new ArrayList<String>(){{
        add("新规划站点编号");add("逻辑站名称");add("下挂基站");add("CRAN机房规划名称");add("CRAN综资机房名称");add("站点配置");add("是否拉远小区");add("综资机房名称");add("设备详细安装位置");add("经度");
        add("纬度");add("关联铁塔公司站址编码");add("是否利旧");add("利旧/共址逻辑站");add("设备厂家");add("数量");add("型号");add("机房共享单位");add("机房产权单位");add("机房配套共享单位");
        add("机房外电共享单位");add("机房面积");add("机房产品分类");add("综资机房名称");add("设备详细安装位置");add("经度");add("纬度");add("关联铁塔公司站址编码");add("数量");add("型号");
        add("是否利旧");add("利旧/共址逻辑站");add("机房共享单位");add("机房产权单位");add("机房配套共享单位");add("机房外电共享单位");add("机房面积");add("机房产品分类");add("是否使用AAU");add("设备详细安装位置");
        add("方位角");add("机械下倾角");add("挂高");add("电下倾角");add("所在铁塔平台");add("设备型号");add("是否有美化外罩");add("天线类型");add("是否利旧");add("利旧天线频段");
        add("是否利旧");add("铁塔产品种类");add("铁塔类型");add("铁塔产权单位");add("铁塔产权性质");add("铁塔共享产权");add("铁塔平台数量");add("铁塔塔身高度(米)");add("已使用平台数量");add("铁塔高度(米)");
        add("板卡名称");add("设备型号");add("填写时间");
    }};
    /**
     * 室外共址-规则使用属性中文名称
     */
    private List<String> oclRulesUseAttributeNameZhs = new ArrayList<String>(){{
        add("新规划站点编号");add("逻辑站名称");add("下挂基站名称");add("CRAN机房规划名称");add("CRAN综资机房名称");add("站点配置");add("是否拉远小区");add("BBU/CU/DU综资机房名称");add("BBU/CU/DU设备详细安装位置");add("BBU/CU/DU经度");
        add("BBU/CU/DU纬度");add("BBU/CU/DU关联铁塔公司站址编码");add("BBU/CU/DU是否利旧");add("BBU/CU/DU利旧/共址逻辑站");add("BBU/CU/DU设备厂家");add("BBU/CU/DU数量");add("BBU/CU/DU型号");add("BBU/CU/DU机房共享单位");add("BBU/CU/DU机房产权单位");add("BBU/CU/DU机房配套共享单位");
        add("BBU/CU/DU机房外电共享单位");add("BBU/CU/DU机房面积");add("BBU/CU/DU机房产品分类");add("RRU/AAU综资机房名称");add("RRU/AAU设备详细安装位置");add("RRU/AAU经度");add("RRU/AAU纬度");add("RRU/AAU关联铁塔公司站址编码");add("RRU/AAU数量");add("RRU/AAU型号");
        add("RRU/AAU是否利旧");add("RRU/AAU利旧/共址逻辑站");add("RRU/AAU机房共享单位");add("RRU/AAU机房产权单位");add("RRU/AAU机房配套共享单位");add("RRU/AAU机房外电共享单位");add("RRU/AAU机房面积");add("RRU/AAU机房产品分类");add("是否使用AAU");add("天线设备详细安装位置");
        add("方位角");add("机械下倾角");add("挂高");add("电下倾角");add("所在铁塔平台");add("天线设备型号");add("是否有美化外罩");add("天线类型");add("天线是否利旧");add("利旧天线频段");
        add("铁塔是否利旧");add("铁塔产品种类");add("铁塔类型");add("铁塔产权单位");add("铁塔产权性质");add("铁塔共享产权");add("铁塔平台数量");add("铁塔塔身高度(米)");add("已使用平台数量");add("铁塔高度(米)");
        add("板卡名称");add("板卡设备型号");add("填写时间");
    }};
    /**
     * 室外共址-数据来源
     */
    private List<String> oclDataSources = new ArrayList<String>(){{
        add("图纸");add("图纸");add("图纸");add("图纸");add("图纸");add("图纸");add("图纸");add("图纸");add("图纸");add("图纸");
        add("图纸");add("网管中心直接从全生命系统、综资提取");add("图纸");add("图纸");add("图纸");add("图纸");add("图纸");add("网管中心直接从全生命系统、综资提取");add("网管中心直接从全生命系统、综资提取");add("网管中心直接从全生命系统、综资提取");
        add("网管中心直接从全生命系统、综资提取");add("网管中心直接从全生命系统、综资提取");add("网管中心直接从全生命系统、综资提取");add("图纸");add("图纸");add("图纸");add("图纸");add("网管中心直接从全生命系统、综资提取");add("图纸");add("图纸");
        add("图纸");add("图纸");add("网管中心直接从全生命系统、综资提取");add("网管中心直接从全生命系统、综资提取");add("网管中心直接从全生命系统、综资提取");add("网管中心直接从全生命系统、综资提取");add("网管中心直接从全生命系统、综资提取");add("网管中心直接从全生命系统、综资提取");add("图纸");add("图纸");
        add("图纸");add("图纸");add("图纸");add("图纸");add("图纸");add("图纸");add("图纸");add("图纸");add("图纸");add("图纸");
        add("图纸");add("48点为“是”无需填写，读取共址逻辑站数据；如“否\"，由设计院填");add("48点为“是”无需填写，读取共址逻辑站数据；如“否\"，由设计院填");add("48点为“是”无需填写，读取共址逻辑站数据；如“否\"，由设计院填");add("48点为“是”无需填写，读取共址逻辑站数据；如“否\"，由设计院填");add("48点为“是”无需填写，读取共址逻辑站数据；如“否\"，由设计院填");add("48点为“是”无需填写，读取共址逻辑站数据；如“否\"，由设计院填");add("48点为“是”无需填写，读取共址逻辑站数据；如“否\"，由设计院填");add("48点为“是”无需填写，读取共址逻辑站数据；如“否\"，由设计院填");add("48点为“是”无需填写，读取共址逻辑站数据；如“否\"，由设计院填");
        add("图纸");add("图纸");add("图纸");
    }};
    /**
     * 室外共址-跟进人
     */
    private List<String> oclForwardPeoples = new ArrayList<String>(){{
        add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");
        add("设计院");add("无忧/徐恒辉");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("无忧/徐恒辉");add("无忧/徐恒辉");add("无忧/徐恒辉");
        add("无忧/徐恒辉");add("无忧/徐恒辉");add("无忧/徐恒辉");add("设计院");add("设计院");add("设计院");add("设计院");add("无忧/徐恒辉");add("设计院");add("设计院");
        add("设计院");add("设计院");add("无忧/徐恒辉");add("无忧/徐恒辉");add("无忧/徐恒辉");add("无忧/徐恒辉");add("无忧/徐恒辉");add("无忧/徐恒辉");add("设计院");add("设计院");
        add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");add("设计院");
        add("设计院");add("设计院/无忧/徐恒辉");add("设计院/无忧/徐恒辉");add("设计院/无忧/徐恒辉");add("设计院/无忧/徐恒辉");add("设计院/无忧/徐恒辉");add("设计院/无忧/徐恒辉");add("设计院/无忧/徐恒辉");add("设计院/无忧/徐恒辉");add("设计院/无忧/徐恒辉");
        add("设计院");add("设计院");add("设计院");
    }};
    /**
     * 室外共址-样例（新增数据填写本列）
     */
    private List<String> oclTemplates = new ArrayList<>();

    public List<String> getOclTemplates() {
        oclTemplates.clear();
        for(int i = 1; i <= oclSerialNumber; i++ ){
            oclTemplates.add(null);
        }
        return oclTemplates;
    }

    /**
     * 室外共址-备注
     */
    private List<String> oclRemarks = new ArrayList<String>(){{
        add(null);add(null);add(null);add(null);add(null);add("以\"/\"间割，室外站点以S开头，如：S1/1/1。室分站点以o开头，如o1/1/1。如果是硬扩：【站点配置】体现新旧配置，例子：原S1/1/1，新S2/2/2/2，填写如下：S1/1/1-S2/1/2/2，o1/1/1/-o2/2/2。如果部分小区不开，填写0，如只开方向4/5小区：0/0/0/1/1。");add(null);add("共址机房找无优徐恒辉提供数据");add(null);add("经纬度需保留小数点后五位");
        add("经纬度需保留小数点后五位");add("铁塔机房必填，获取来源接口待确认");add(null);add("如果是利旧BBU，填写所利旧逻辑站名；如果BBU共址，填写共址逻辑站名称（优先填写在用4G站点）");add("中兴、爱立信、华为");add("如与其他系统共BBU，数量填0");add(null);add("枚举值：电信、电信+联通、联通、无");add("枚举值：第三方、其它、业主、中国广电、中国铁塔、中国铁通、中国移动");add("枚举值：电信、电信+联通、联通、无");
        add("枚举值：电信、电信+联通、联通、无");add("单位为（平方米），一体化机柜按实际数量，挂墙等非标机房填1");add("枚举值：RRU拉远、彩钢板、框架、其他、无机房、一体化机房、一体化机柜、移动自维、砖混、租用");add("共址机房找无优徐恒辉提供数据");add("统一按大数据规划天面地址填写");add("经纬度需保留小数点后五位，统一按大数据规划经纬度填写");add("经纬度需保留小数点后五位，统一按大数据规划经纬度填写");add("铁塔机房必填，获取来源接口待确认");add("以\"/\"间割，【RRU/AAU数量】与【站点配置】格式保持一致。如与其他系统共RRU/AAU，数量填0");add("先按设计输出填写，施工领货与设计部符时后期让施工核对修正");
        add(null);add("如果是利旧AAU/RRU，填写所利旧逻辑站名；如果AAU/RRU共址，填写共址逻辑站名称（优先填写在用4G站点）");add("枚举值：电信、电信+联通、联通、无");add("枚举值：第三方、其它、业主、中国广电、中国铁塔、中国铁通、中国移动");add("枚举值：电信、电信+联通、联通、无");add("枚举值：电信、电信+联通、联通、无");add("单位为（平方米），如安装在天面直接填1");add("枚举值：RRU拉远、彩钢板、框架、其他、无机房、一体化机房、一体化机柜、移动自维、砖混、租用");add(null);add(null);
        add("以“/”为间隔");add("以“/”为间隔");add("以“/”为间隔");add("以“/”为间隔");add("以“/”为间隔");add("与AAU一体化需填AAU型号，先按天线类型填写后期由施工修正");add(null);add(null);add(null);add("如果是利旧天线，以“/”为间隔，与【站点配置】格式保持一致。每个小区只填写一个利旧的天线频段，如果其中一个天线是新增则为空。如：GS//DC，则表示1和3小区是利旧GS和DC，2小区是新增天线。");
        add(null);add("（多种铁塔产品种类时以“/”来增加）枚举值：自维铁塔、普通地面塔、景观塔、简易塔、普通楼面塔、楼面抱杆");add("（多种铁塔产品种类时以“/”来增加）枚举值：18米等迳杆,H杆,抱杆,壁挂,便携式一体化箱体,地面H杆,地面灯塔,地面拉线塔,地面自立塔,房顶四角塔,仿生树,杆形塔,钢管,钢管塔,广告牌,集束天线,监控杆,空调美化,空调型,立杆,楼顶单管塔,楼顶角钢塔,楼顶井字架,楼顶景观塔,楼顶拉线塔,楼顶美化天线,楼顶三管塔,楼顶围杆塔,落地角钢塔,落地景观塔,落地拉线塔,落地内爬单管塔,落地三管塔,落地外爬单管塔,美化桶,排气管美化,升降塔,水泥杆,围杆塔,屋面立杆,屋面围杆塔,屋面自立塔,烟囱美化,增高架");add("枚举值：中国铁塔、中国移动、中国联通、中国电信、中国铁通、中国广电、业主、其他、第三方");add("枚举值：自有（自建）、自有（合建）、自有（购买）、租用、用户所有");add("枚举值：电信,电信+联通,联通,铁塔,北讯，电信+联通+北讯，电信+北讯，联通+北讯，铁塔+北讯，无");add(null);add(null);add("平台数量以实际数量计算，包括共建共享单位使用的平台，抱杆没有平台填1");add(null);
        add("多个板卡以“/”为间隔");add("多个板卡以“/”为间隔");add("设计方案大表填写时间，不能带公式");
    }};
    /**
     * 室外共址后端输入字段map
     */
    private Map<String, String> oclTemplateMap = new HashMap<String, String>(){{
        put("1","newPlanSiteNumber");
        put("2","logicSiteName");
        put("3","downTiltBaseSiteName");
        put("6","carrierProperties");
        put("9","bbuCuDuDeviceDetailInstallSite");//BBU/CU/DU设备详细安装位置
        put("10","bbuCuDuLongitude");//BBU/CU/DU经度

        put("11","bbuCuDuLatitude");//BBU/CU/DU纬度
        put("13","bbuCuDuIsProfitUsed");
        put("14","bbuCuDuProfitUsedCoLocationLogicSite");
        put("15","productFactory");
        put("16","bbuNumber");
        put("17","deviceModel");

//        put("25","deviceDetailInstallSite");
        put("26","longitudeRru");
        put("27","latitudeRru");
        put("29","rruAauNumber");
        put("30","rruAauModel");

        put("31","rruAauIsProfitUsed");
        put("32","rruAauProfitUsedCoLocationLogicSite");
        put("39","isUsedAau");
        put("40","deviceDetailInstallSite");

        put("41", "azimuth");
        put("42", "mechanicalDownTiltAngle");
        put("43", "rideHeight");

        put("44", "electricDownTiltAngle");
        put("45", "twoBelongIronTowerPlatform");
        put("46", "antennaDeviceModel");
        put("47", "isHadBeautyOuterCover");
        put("49", "antennaNumber");
        put("51", "twoIronTowerIsProfitUsed");
        put("52", "ironTowerProductKind");
        put("53", "twoIronTowerType");
        put("54", "ironTowerEquityUnit");

        put("55", "ironTowerEquityCharacter");
        put("56", "ironTowerPublicEquity");
        put("57", "twoIronTowerPlatformNumber");
        put("58", "twoIronTowerTowerBodyHeightMeter");
        put("59", "usedPlatformNumber");
        put("60", "ironTowerHeightMeter");
        put("61","boardNameNumber");
        put("62","boardNameNumber");
    }};
}
