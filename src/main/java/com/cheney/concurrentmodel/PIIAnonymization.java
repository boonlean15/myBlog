package com.cheney.concurrentmodel;

import java.util.HashMap;
import java.util.Map;

public class PIIAnonymization {

    // 同义替换映射
    private static final Map<String, String> SYNONYMS = new HashMap<>();

    static {
        SYNONYMS.put("张", "张");
        SYNONYMS.put("王", "王");
        SYNONYMS.put("李", "李");
        // ... 其他姓名同义字
    }

    public static String anonymizeName(String name) {
        String firstName = name.substring(0, 1);
        // 使用同义字替换姓名中的姓
        String surname = name.substring(1);
        int length = surname.length();
        String anonStr = "";
        for(int i = 0; i < length; i++){
            anonStr += "*";
        }
        String anonymizeName = firstName + anonStr;
        return anonymizeName;
    }

    public static String anonymizePhone(String phone) {
        int length = phone.length();
        String lastNum = phone.substring(length - 4, length);
        String preNum = phone.substring(0, length - 4);
        String anonStr = "";
        for(int i = 0; i < preNum.length(); i++){
            anonStr += "*";
        }
        return anonStr + lastNum;
    }

    public static void main(String[] args) {
        String originalName = "张三水水水水";
        String anonymizedName = anonymizeName(originalName);
        System.out.println("原始姓名: " + originalName + ", 去标识化姓名: " + anonymizedName);

        String originalPhone = "13512755131";
        String anonymizePhone = anonymizePhone(originalPhone);
        System.out.println("原始phone: " + originalPhone + ", 去标识化phone: " + anonymizePhone);
    }
}
