package com.cheney.test.easyexcel.listener;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.util.StringUtils;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cheney.test.easyexcel.entity.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author linch
 * @create 2022/1/13 17:00
 */
@Slf4j
public class ResultExcelListener extends AnalysisEventListener<ResultExcel> {

    private List<String> headList;

    public List<String> getHeadList(){
        return headList;
    }

    private CadHelpExcel cadHelpExcel;

    private List<ResultExcel> datas;
    private List<String> notTemplate;

    @SneakyThrows
    @Override
    public void invoke(ResultExcel resultExcel, AnalysisContext analysisContext) {

        datas.add(resultExcel);
        log.info("解析到一条数据:{}", JSON.toJSONString(resultExcel));
        //1.根据名称确定模板
        //逻辑站名称
        String logicSiteName = resultExcel.getLogicSiteName();
        log.info("逻辑站名称 ----- " + logicSiteName);
        //共站情况
        String coSiteSituation = resultExcel.getCoSiteSituation();
        log.info("共站情况 ----- " + coSiteSituation);
        //文件名
        String fileNameZh = resultExcel.getFileNameZh();
        //输出临时文件夹
        String path = "H:\\润建\\图纸项目\\输出\\";
        String excelFileName = path + fileNameZh + ".xlsx";
        log.info("文件名 ------ " + excelFileName);
        OutPutFileType excelTemplate = getExcelTemplate(logicSiteName, coSiteSituation, fileNameZh);
        setSheetName(excelTemplate);

        Map<String, String> templateMap = new HashMap<>();
        List<String> templates = new ArrayList<>();
        List<String> serialNumbers = new ArrayList<>();
        switch (excelTemplate){
            case IN_NEW:
                templateMap = cadHelpExcel.getInlTemplateMap();
                templates = cadHelpExcel.getInlTemplates();
                serialNumbers = cadHelpExcel.getInlSerialNumbers();
                break;
            case IN_CO:
                templateMap = cadHelpExcel.getIclTemplateMap();
                templates = cadHelpExcel.getIclTemplates();
                serialNumbers = cadHelpExcel.getIclSerialNumbers();
                break;
            case OUT_NEW:
                templateMap = cadHelpExcel.getOnlTemplateMap();
                templates = cadHelpExcel.getOnlTemplates();
                serialNumbers = cadHelpExcel.getOnlSerialNumbers();
                break;
            case OUT_CO:
                templateMap = cadHelpExcel.getOclTemplateMap();
                templates = cadHelpExcel.getOclTemplates();
                serialNumbers = cadHelpExcel.getOclSerialNumbers();
                break;
            case NOT:
                notTemplate.add(excelFileName);
                break;
            default:
                break;
        }
        if(excelTemplate.equals(OutPutFileType.NOT)){
            return;
        }
        //°和，删掉 longitudeRru latitudeRru siteLongitude siteLatitude bbuCuDuLongitude bbuCuDuLatitude
        replaceChar(resultExcel);
        setTemplates(resultExcel, serialNumbers, templateMap, templates);
        outPutExcel(excelTemplate, excelFileName, templates);
    }

    /**
     * 设置模板值
     * @param resultExcel
     * @param serialNumbers
     * @param templateMap
     * @param templates
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void setTemplates(ResultExcel resultExcel, List<String> serialNumbers,Map<String, String> templateMap, List<String> templates)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd  HH:ss:mm");
        Field[] fields = ResultExcel.class.getDeclaredFields();
        for(int i = 0; i < fields.length; i++){
            Field field = fields[i];
            String fieldName = field.getName();
            log.info("ResultExcel 字段名 ---- " + fieldName);
            String firstLetter = fieldName.substring(0,1).toUpperCase();
            //获得和属性对应的getXXX()方法的名字
            String getMethodName = "get"+firstLetter+fieldName.substring(1);
            //获得和属性对应的getXXX()方法
            Method getMethod = ResultExcel.class.getMethod(getMethodName);
            //调用拷贝对象的setXXX()方法
            String fieldValue = (String) getMethod.invoke(resultExcel);
            log.info("i ====== " + i + "获取的字段值 ----- " + fieldValue);
            for(int j = 1; j <= serialNumbers.size(); j++){
                if(templateMap.get(j+"") != null && templateMap.get(j+"").equals(fieldName)){
                        if(StringUtils.isNotBlank(fieldValue)){
                        if(fieldName.equals("boardNameNumber")){
                            String replaceFieldValue = fieldValue.replace("{", "").replace("}", "").replace("'", "");
                            String[] split = replaceFieldValue.split("，");
                            List<String> fieldValueList = Arrays.asList(split);
                            List<String> collect = fieldValueList.stream().map(v -> v.split(":")[0]).collect(Collectors.toList());
                            StringBuilder keyBoard = new StringBuilder();
                            StringBuilder keyNumber = new StringBuilder();
                            for(int k = 0; k < collect.size(); k++){
                                int board = collect.get(k).lastIndexOf("板");
                                String subBoard = collect.get(k).substring(0, board + 1);
                                String subNumber = collect.get(k).substring(board + 1);
                                if(StringUtils.isNotBlank(subBoard)){
                                    keyBoard.append(subBoard);
                                    if(k < collect.size() - 1){
                                        keyBoard.append("/");
                                    }
                                }
                                if(StringUtils.isNotBlank(subNumber)){
                                    keyNumber.append(subNumber);
                                    if(k < collect.size() - 1){
                                        keyNumber.append("/");
                                    }
                                }
                            }
                            if(StringUtils.isNotBlank(keyBoard.toString())){
                                boolean b = keyBoard.toString().endsWith("/");
                                String substring1 = keyBoard.substring(0, keyBoard.length() - 1);
                                templates.set(j - 1, b ? substring1 : keyBoard.toString());
                            }
                            if(StringUtils.isNotBlank(keyNumber.toString())){
                                boolean b1 = keyNumber.toString().endsWith("/");
                                String substring = keyNumber.substring(0, keyNumber.length() - 1);
                                templates.set(j, b1 ? substring : keyNumber.toString());
                            }
                            //填写时间
                            Date date = new Date();
                            String format = simpleDateFormat.format(date);
                            templates.set(serialNumbers.size() - 1, format);
                        }else
//                            if(fieldName.equals("deviceDetailInstallSite")){
//                            templates.set(j - 1, fieldValue);
//                            templates.set(40 - 1, fieldValue);
//                        } else
                        {
                            //-1 map是序号，list还是index
                            templates.set(j - 1, fieldValue);
                        }
                        log.info("templates --------- " + templates);
                        break;
                    }
                }
            }
        }
    }

    /**
     * 替换表对象的一些特殊字段内容
     * @param resultExcel
     */
    private void replaceChar(ResultExcel resultExcel){
        //°和，删掉 longitudeRru latitudeRru siteLongitude siteLatitude bbuCuDuLongitude bbuCuDuLatitude
        String longitudeRru = resultExcel.getLongitudeRru();
        String latitudeRru = resultExcel.getLatitudeRru();
        String siteLongitude = resultExcel.getSiteLongitude();
        String siteLatitude = resultExcel.getSiteLatitude();
        String bbuCuDuLongitude = resultExcel.getBbuCuDuLongitude();
        String bbuCuDuLatitude = resultExcel.getBbuCuDuLatitude();
        if(StringUtils.isNotBlank(longitudeRru)){
            resultExcel.setLongitudeRru(longitudeRru.replace("°","").replace("，","").replace(":",""));
        }
        if(StringUtils.isNotBlank(latitudeRru)){
            resultExcel.setLatitudeRru(latitudeRru.replace("°","").replace("，","").replace(":",""));
        }
        if(StringUtils.isNotBlank(siteLongitude)){
            resultExcel.setSiteLongitude(siteLongitude.replace("°","").replace("，","").replace(":",""));
        }
        if(StringUtils.isNotBlank(siteLatitude)){
            resultExcel.setSiteLatitude(siteLatitude.replace("°","").replace("，","").replace(":",""));
        }
        if(StringUtils.isNotBlank(bbuCuDuLongitude)){
            resultExcel.setBbuCuDuLongitude(bbuCuDuLongitude.replace("°","").replace("，","").replace(":",""));
        }
        if(StringUtils.isNotBlank(bbuCuDuLatitude)){
            resultExcel.setBbuCuDuLatitude(bbuCuDuLatitude.replace("°","").replace("，","").replace(":",""));
        }
    }

    private void outPutExcel(OutPutFileType excelTemplate, String excelFileName, List<String> templates){
        switch (excelTemplate){
            case IN_NEW:
                writeExcel(cadHelpExcel.getInlSerialNumbers(), cadHelpExcel.getInlInstances(), cadHelpExcel.getInlPropertyNameZhs(),
                        cadHelpExcel.getInlRulesUseAttributeNameZhs(), cadHelpExcel.getInlDataSources(), cadHelpExcel.getInlForwardPeoples(),
                        templates, cadHelpExcel.getInlRemarks(), excelFileName);
                break;
            case IN_CO:
                writeExcel(cadHelpExcel.getIclSerialNumbers(), cadHelpExcel.getIclInstances(), cadHelpExcel.getIclPropertyNameZhs(),
                        cadHelpExcel.getIclRulesUseAttributeNameZhs(), cadHelpExcel.getIclDataSources(), cadHelpExcel.getIclForwardPeoples(),
                        templates, cadHelpExcel.getIclRemarks(), excelFileName);
                break;
            case OUT_NEW:
                //计算公式 ： 需要把、换成/
                setTemplateValue(templates);
                writeExcel(cadHelpExcel.getOnlSerialNumbers(), cadHelpExcel.getOnlInstances(), cadHelpExcel.getOnlPropertyNameZhs(),
                        cadHelpExcel.getOnlRulesUseAttributeNameZhs(), cadHelpExcel.getOnlDataSources(), cadHelpExcel.getOnlForwardPeoples(),
                        templates, cadHelpExcel.getOnlRemarks(), excelFileName);
                break;
            case OUT_CO:
                //计算公式 ： 需要把、换成/
                setTemplateValue(templates);
                writeExcel(cadHelpExcel.getOclSerialNumbers(), cadHelpExcel.getOclInstances(), cadHelpExcel.getOclPropertyNameZhs(),
                        cadHelpExcel.getOclRulesUseAttributeNameZhs(), cadHelpExcel.getOclDataSources(), cadHelpExcel.getOclForwardPeoples(),
                        templates, cadHelpExcel.getOclRemarks(), excelFileName);
                break;
            default:
                break;
        }
    }

    private void setTemplateValue(List<String> templates){
        if(StringUtils.isNotBlank(templates.get(40))){
            templates.set(40, templates.get(40).replace("、","/"));
        }
        if(StringUtils.isNotBlank(templates.get(41))){
            templates.set(41, templates.get(41).replace("、","/"));
        }
        if(StringUtils.isNotBlank(templates.get(42))){
            templates.set(42, templates.get(42).replace("、","/"));
        }
        if(StringUtils.isNotBlank(templates.get(43))){
            templates.set(43, templates.get(43).replace("、","/"));
        }
        if(StringUtils.isNotBlank(templates.get(44))){
            templates.set(44, templates.get(44).replace("、","/"));
        }
        String cs = templates.get(48);
        if(StringUtils.isNotBlank(cs) && Integer.parseInt(cs) > 0){
            templates.set(48,NO);
        }else {
            templates.set(48,YES);
        }
    }

    private void writeExcel(List<String> serialNumbers, List<String> instances, List<String> propertyNameZhs, List<String> rulesUseAttributeNameZhs,
                            List<String> dataSources, List<String> forwardPeoples, List<String> templates,List<String> remarks,
                            String excelFileName){
        List<OutPutExcel> outPutExcels = new ArrayList<>();
        for(int i = 0; i < serialNumbers.size(); i++){
            OutPutExcel outPutExcel = new OutPutExcel();
            outPutExcel.setSerialNumber(serialNumbers.get(i));
            outPutExcel.setInstance(instances.get(i));
            outPutExcel.setPropertyNameZh(propertyNameZhs.get(i));
            outPutExcel.setRulesUseAttributeNameZh(rulesUseAttributeNameZhs.get(i));
            outPutExcel.setDataSource(dataSources.get(i));
            outPutExcel.setForwardPeople(forwardPeoples.get(i));
            outPutExcel.setTemplate(templates.get(i));
            outPutExcel.setRemark(remarks.get(i));
            outPutExcels.add(outPutExcel);
        }
        if(excelFileName.contains("?")){
            excelFileName = excelFileName.replace("?", "");
        }
        EasyExcel.write(excelFileName, OutPutExcel.class).sheet(sheetName).doWrite(outPutExcels);
    }


    private final String END_WORD_W = "W";
    private final String END_WORD_H = "H";

    private final String NEW_LOCATION = "新址";
    private final String CO_LOCATION = "共址";

    private final String IN_DOOR = "室分";
    private final String OUT_DOOR = "室外";

    private final String YES = "是";
    private final String NO = "否";

    /**
     * 填写不同的excel模板
     * 设计方案大表 (室分新址)：【逻辑站名称】末尾为“W”,【共站情况】包含“新址”
     * 设计方案大表 (室分共址)：【逻辑站名称】末尾为“W”,【共站情况】包含“共址”
     * 设计方案大表 (室外新址)：【逻辑站名称】末尾为“H”,【共站情况】包含“新址”
     * 设计方案大表 (室外共址)：【逻辑站名称】末尾为“H”,【共站情况】包含“共址”
     * @return
     */
    private OutPutFileType getExcelTemplate(String logicSiteName, String coSiteSituation, String fileNameZh){
        if(StringUtils.isNotBlank(logicSiteName)){
            if(logicSiteName.endsWith(END_WORD_W)){
                if(StringUtils.isNotBlank(coSiteSituation)){
                    if(coSiteSituation.contains(NEW_LOCATION)){
                        return OutPutFileType.IN_NEW;
                    }
                    if(coSiteSituation.contains(CO_LOCATION)){
                        return OutPutFileType.IN_CO;
                    }
                }else {
                    //这部分，增加判断文件名包含“【共址】”或“【新址】”来区分，如果包含“【共址】”，那就是匹配共址那2种情况
                    if(StringUtils.isNotBlank(fileNameZh)){
                        if(fileNameZh.contains(NEW_LOCATION)){
                            return OutPutFileType.IN_NEW;
                        }
                        if(fileNameZh.contains(CO_LOCATION)){
                            return OutPutFileType.IN_CO;
                        }
                    }
                }
            }
            if(logicSiteName.endsWith(END_WORD_H)){
                if(StringUtils.isNotBlank(coSiteSituation)){
                    if(coSiteSituation.contains(NEW_LOCATION)){
                        return OutPutFileType.OUT_NEW;
                    }
                    if(coSiteSituation.contains(CO_LOCATION)){
                        return OutPutFileType.OUT_CO;
                    }
                }else {
                    //这部分，增加判断文件名包含“【共址】”或“【新址】”来区分，如果包含“【共址】”，那就是匹配共址那2种情况
                    if(StringUtils.isNotBlank(fileNameZh)){
                        if(fileNameZh.contains(NEW_LOCATION)){
                            return OutPutFileType.OUT_NEW;
                        }
                        if(fileNameZh.contains(CO_LOCATION)){
                            return OutPutFileType.OUT_CO;
                        }
                    }
                }
            }
        }
        if(StringUtils.isNotBlank(fileNameZh)){
            if(fileNameZh.contains(NEW_LOCATION) && fileNameZh.contains(IN_DOOR)){
                return OutPutFileType.IN_NEW;
            }
            if(fileNameZh.contains(CO_LOCATION) && fileNameZh.contains(IN_DOOR)){
                return OutPutFileType.IN_CO;
            }
            if(fileNameZh.contains(NEW_LOCATION) && fileNameZh.contains(OUT_DOOR)){
                return OutPutFileType.OUT_NEW;
            }
            if(fileNameZh.contains(CO_LOCATION) && fileNameZh.contains(OUT_DOOR)){
                return OutPutFileType.OUT_CO;
            }
        }
        return OutPutFileType.NOT;
    }

    /**
     * xlsx文件用【文件名】命名，但不要.pdf
     * @param fileNameZh
     * @return
     */
    private String getExcelFileName(String fileNameZh){
        return fileNameZh.substring(0,fileNameZh.length() - 4);
    }

    private String sheetName = "默认sheet表名";

    private void setSheetName(OutPutFileType excelTemplate){
        switch (excelTemplate){
            case IN_NEW:
                sheetName = "设计方案大表 (室分新址)";
                break;
            case IN_CO:
                sheetName = "设计方案大表 (室分共址)";
                break;
            case OUT_NEW:
                sheetName = "设计方案大表 (室外新址)";
                break;
            case OUT_CO:
                sheetName = "设计方案大表 (室外共址)";
                break;
            default:
                break;
        }
    }

    /**
     * 解析后输出PMI表
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //输出pmi表
        String excelName = "H:\\润建\\图纸项目\\输出\\PMIS.xlsx";
        List<OutPutPmiExcel> outs = new ArrayList<>();
        datas.forEach(data -> {
            OutPutPmiExcel out = new OutPutPmiExcel();
            setOutPutExcelObject(data, out);
            outs.add(out);
        });
        EasyExcel.write(excelName, OutPutPmiExcel.class).sheet("PMIS").doWrite(outs);
        log.info("没有模板匹配的文件 ---------- " + notTemplate);
    }


    private int one = 1;
    private int two = 2;
    private int three = 3;
    private int four = 4;

    /**
     * 设置输出对象值
     * @param data
     * @param out
     */
    private void setOutPutExcelObject(ResultExcel data, OutPutPmiExcel out){
        out.setPlanSiteName(data.getLogicSiteName());
        out.setLongitude(data.getBbuCuDuLongitude());
        out.setLatitude(data.getBbuCuDuLatitude());
        out.setLocation(data.getBbuCuDuDeviceDetailInstallSite());
        out.setBuildProperty(data.getCoSiteSituation());
        out.setCarrierProperties(data.getCarrierProperties());

        setPlotCount(data, out);
        setAzimuth(data, out);
        setTotalDownTiltAngle(data, out);
        setRideHeight(data, out);
    }

    private void setPlotCount(ResultExcel data, OutPutPmiExcel out){
        //先计算【载波配置】的/数，然后加1，例如S1，那么就是填1，S1/1,那么就是填2
        String carrierProperties = data.getCarrierProperties();
        if(StringUtils.isNotBlank(carrierProperties)){
            boolean contains = carrierProperties.contains("/");
            if(!contains){
                out.setPlotCount("1");
            }else{
                String[] split = carrierProperties.split("/");
                out.setPlotCount(split.length + "");
            }
        }
    }

    private void setAzimuth(ResultExcel data, OutPutPmiExcel out){
        //【方位角】按、拆分，然后按顺序填入方向角1、方向角2、方向角3、方向角4
        String azimuth = data.getAzimuth();
        if(StringUtils.isNotBlank(azimuth)){
            String[] split = azimuth.split("、");
            int length = split.length;
            if(length == one){
                out.setAzimuth1(split[0]);
            }
            if(length == two){
                out.setAzimuth1(split[0]);
                out.setAzimuth2(split[1]);
            }
            if(length == three){
                out.setAzimuth1(split[0]);
                out.setAzimuth2(split[1]);
                out.setAzimuth3(split[2]);
            }
            if(length == four){
                out.setAzimuth1(split[0]);
                out.setAzimuth2(split[1]);
                out.setAzimuth3(split[2]);
                out.setAzimuth4(split[3]);
            }
        }
    }

    private void setTotalDownTiltAngle(ResultExcel data, OutPutPmiExcel out){
        //【总下倾角】按、拆分，然后按顺序填入下倾角1、下倾角2、下倾角3、下倾角4
        String totalDownTiltAngle = data.getTotalDownTiltAngle();
        if(StringUtils.isNotBlank(totalDownTiltAngle)){
            String[] split = totalDownTiltAngle.split("、");
            int length = split.length;
            if(length == one){
                out.setTotalDownTiltAngle1(split[0]);
            }
            if(length == two){
                out.setTotalDownTiltAngle1(split[0]);
                out.setTotalDownTiltAngle2(split[1]);
            }
            if(length == three){
                out.setTotalDownTiltAngle1(split[0]);
                out.setTotalDownTiltAngle2(split[1]);
                out.setTotalDownTiltAngle3(split[2]);
            }
            if(length == four){
                out.setTotalDownTiltAngle1(split[0]);
                out.setTotalDownTiltAngle2(split[1]);
                out.setTotalDownTiltAngle3(split[2]);
                out.setTotalDownTiltAngle4(split[3]);
            }
        }
    }

    private void setRideHeight(ResultExcel data, OutPutPmiExcel out){
        //【挂高】按、拆分，然后按顺序填入挂高1、挂高2、挂高3、挂高4
        String rideHeight = data.getRideHeight();
        if(StringUtils.isNotBlank(rideHeight)){
            String[] split = rideHeight.split("、");
            int length = split.length;
            if(length == one){
                out.setRideHeight1(split[0]);
            }
            if(length == two){
                out.setRideHeight1(split[0]);
                out.setRideHeight2(split[1]);
            }
            if(length == three){
                out.setRideHeight1(split[0]);
                out.setRideHeight2(split[1]);
                out.setRideHeight3(split[2]);
            }
            if(length == four){
                out.setRideHeight1(split[0]);
                out.setRideHeight2(split[1]);
                out.setRideHeight3(split[2]);
                out.setRideHeight4(split[3]);
            }
        }
    }

    @Override
    public void invokeHead(Map<Integer, ReadCellData<?>> headMap, AnalysisContext context) {
        super.invokeHead(headMap, context);
        log.info("头 ---------- 解析到一条头数据:{}", JSON.toJSONString(headMap));
        List<String> heads = headMap.values().stream().map(v -> v.getStringValue()).collect(Collectors.toList());
        log.info(heads.toString());
        headList = heads;
        cadHelpExcel = new CadHelpExcel();
        cadHelpExcel.setHeads(heads);
        datas = new ArrayList<>();
        notTemplate = new ArrayList<>();
    }
}
