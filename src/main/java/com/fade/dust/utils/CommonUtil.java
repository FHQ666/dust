package com.fade.dust.utils;

public class CommonUtil {
    /**
     * 将字符串的首字母转大写
     * @param str 需要转换的字符串
     * @return
     */
    public static String captureName(String fieldName) {
        if(fieldName == null || fieldName.length() < 1) {
            return fieldName;
        }
        return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }
}
