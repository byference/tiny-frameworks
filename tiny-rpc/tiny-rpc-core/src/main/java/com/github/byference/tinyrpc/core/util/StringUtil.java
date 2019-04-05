package com.github.byference.tinyrpc.core.util;

/**
 * StringUtil
 *
 * @author byference
 * @since 2019/04/05
 */
public class StringUtil {


    /**
     * 将字符串的首字母小写
     * @param str 待处理字符串
     * @return 首字母小写后的字符串
     */
    public static String lowerFirst(String str) {
        char[] chars = str.toCharArray();
        if (Character.isUpperCase(chars[0])) {
            chars[0] += 32;
        }
        return String.valueOf(chars);
    }

}
