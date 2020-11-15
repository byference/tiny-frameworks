package io.github.byference.spring.util;

/**
 * StringUtil
 *
 * @author byference
 * @since 2020-10-08
 */
public class StringUtil {

    private StringUtil() {}


    public static String toLowFirstCase(String targetStr) {
        if (isEmpty(targetStr)) {
            return targetStr;
        }
        char[] chars = targetStr.toCharArray();
        if (chars[0] >= 97) {
            return targetStr;
        }
        chars[0] += 32;
        return String.valueOf(chars);
    }

    public static boolean isEmpty(String targetStr) {
        return targetStr == null || targetStr.isEmpty();
    }

    public static boolean isNotEmpty(String targetStr) {
        return !isEmpty(targetStr);
    }

}
