package com.cheney.pdftest;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class StrUtils {
    /**
     *
     * @param text
     * @return
     */
    public static List<String> lines(String text){
        if(text == null || text.isEmpty()){
            return null;
        }
        return Lists.newArrayList(text.split("\n+"));
    }
    /**
     *
     * @param text
     * @param lineIndex
     * @return
     */
    public static String splitAt(String text, int lineIndex){
        if(text == null || text.isEmpty()){
            return null;
        }
        String[] lines = text.split("\\s+");
        return lines.length > lineIndex ? lines[lineIndex] : null;
    }
    /**
     * 读取某行的数据
     *
     * @param text
     * @param lineIndex 0 开始
     * @return
     */
    public static String lineAt(String text, int lineIndex){
        if(text == null || text.isEmpty()){
            return null;
        }
        String[] lines = text.split("\r?\n");
        return lines.length > lineIndex ? lines[lineIndex] : null;
    }
    /**
     *
     *
     * @param text
     * @param startFlag 最后开始第一个
     * @param closeFlag 最后开始第一个
     * @return
     */
    public static String substringBetween(String text, String startFlag, String closeFlag, String subType){
        return substringBetween(text, startFlag, closeFlag, subType, null);
    }
    /**
     *
     *
     * @param text
     * @param startFlag 最后开始第一个
     * @param closeFlag 最后开始第一个
     * @return
     */
    public static String substringBetween(String text, String startFlag, String closeFlag, String subType, String defaultVal){
        if(StringUtils.isEmpty(text)){
            return defaultVal;
        }
        if("substringBetween".equals(subType)){
            if(StringUtils.isNotEmpty(startFlag) && StringUtils.isNotEmpty(closeFlag)){
                return StrUtils.substringBetween(text, startFlag, closeFlag);
            }
        } else if("substringBetweenLast".equals(subType)){
            if(StringUtils.isNotEmpty(startFlag) && StringUtils.isNotEmpty(closeFlag)){
                return StrUtils.substringBetweenLast(text, startFlag, closeFlag);
            }
        } else if("substringBetweenLastBefore".equals(subType)){
            if(StringUtils.isNotEmpty(startFlag) && StringUtils.isNotEmpty(closeFlag)){
                return StrUtils.substringBetweenLastBefore(text, startFlag, closeFlag);
            }
        } else if("substringBetweenBeforeLast".equals(subType)){
            if(StringUtils.isNotEmpty(startFlag) && StringUtils.isNotEmpty(closeFlag)){
                return StrUtils.substringBetweenBeforeLast(text, startFlag, closeFlag);
            }
        } else if("substringAfter".equals(subType)){
            if(StringUtils.isNotEmpty(startFlag)){
                return StringUtils.substringAfter(text, startFlag);
            }
        } else if("substringAfterLast".equals(subType)){
            if(StringUtils.isNotEmpty(startFlag)){
                return StringUtils.substringAfterLast(text, startFlag);
            }
        } else if("substringBefore".equals(subType)){
            if(StringUtils.isNotEmpty(closeFlag)){
                return StringUtils.substringBefore(text, closeFlag);
            }
        } else if("substringBeforeLast".equals(subType)){
            if(StringUtils.isNotEmpty(closeFlag)){
                return StringUtils.substringBeforeLast(text, closeFlag);
            }
        }
        return defaultVal;
    }
    /**
     *
     * @param text
     * @param startFlag 前面开始第一个
     * @param closeFlag 前面开始第一个
     * @return
     */
    public static String substringBetween(String text, String startFlag, String closeFlag){
        if(text == null || text.isEmpty()){
            return null;
        }
        int startIndex = text.indexOf(startFlag);
        int endIndex   = text.indexOf(closeFlag);
        if(startIndex >= 0 && endIndex >= 0 && endIndex >= startIndex){
            return text.substring(startIndex+startFlag.length(), endIndex);
        }
        return null;
    }

    public static String substringBetween(String text, String startFlag){
        if(text == null || text.isEmpty()){
            return null;
        }
        int startIndex = text.indexOf(startFlag);
        if(startIndex >= 0 && startIndex != -1){
            return text.substring(startIndex + startFlag.length());
        }
        return null;
    }

    /**
     *
     *
     * @param text
     * @param startFlag 最后开始第一个
     * @param closeFlag 最后开始第一个
     * @return
     */
    public static String substringBetweenLast(String text, String startFlag, String closeFlag){
        if(text == null || text.isEmpty()){
            return null;
        }
        int startIndex = text.lastIndexOf(startFlag);
        int endIndex   = text.lastIndexOf(closeFlag);
        if(startIndex >= 0 && endIndex >= 0 && endIndex >= startIndex){
            return text.substring(startIndex+startFlag.length(), endIndex);
        }
        return null;
    }

    /**
     *
     *
     * @param text
     * @param startFlag 最后开始第一个
     * @param closeFlag 前面开始第一个
     * @return
     */
    public static String substringBetweenLastBefore(String text, String startFlag, String closeFlag){
        if(text == null || text.isEmpty()){
            return null;
        }
        int startIndex = text.lastIndexOf(startFlag);
        int endIndex   = text.indexOf(closeFlag);
        if(startIndex >= 0 && endIndex >= 0 && endIndex >= startIndex){
            return text.substring(startIndex+startFlag.length(), endIndex);
        }
        return null;
    }

    /**
     *
     * @param text
     * @param startFlag 前面开始第一个
     * @param closeFlag 最后开始第一个
     * @return
     */
    public static String substringBetweenBeforeLast(String text, String startFlag, String closeFlag){
        if(text == null || text.isEmpty()){
            return null;
        }
        int startIndex = text.indexOf(startFlag);
        int endIndex   = text.lastIndexOf(closeFlag);
        if(startIndex >= 0 && endIndex >= 0 && endIndex >= startIndex){
            return text.substring(startIndex+startFlag.length(), endIndex);
        }
        return null;
    }

    /**
     * Null转换成空
     *
     * @param val
     * @return
     */
    public static String nullToEmpty(String val){
        return val == null ? "" : val;
    }

    /**
     * 删除回车键
     *  回车Carriage Return
     *
     * @param val
     * @return
     */
    public static String removeReturnChar(String val){
        return val == null ? "" : val.replace("\r", "");
    }


    public static String getLastNotNullText(String[] array){
        List<String> collect = Arrays.stream(array).filter(x -> StrUtil.isNotBlank(x)).collect(Collectors.toList());
        return collect.get(collect.size() - 1);
    }
    public static String getFirstNotNullText(String[] array){
        List<String> collect = Arrays.stream(array).filter(x -> StrUtil.isNotBlank(x)).collect(Collectors.toList());
        return collect.get(0);
    }

}

