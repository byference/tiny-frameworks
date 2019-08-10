package com.github.byference.mybatis.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * RegexUtil
 *
 * @author byference
 * @since 2019-08-10
 */
public class RegexUtil {


    private static final Pattern UNDERLINE_HUMP_PATTERN = Pattern.compile("_(\\w)");


    private RegexUtil() {}


    /**
     * 下划线转驼峰
     *
     * @param underline underline
     * @return hump
     */
    public static String underline2Hump(String underline) {

        Matcher matcher = UNDERLINE_HUMP_PATTERN.matcher(underline);
        StringBuffer sb = new StringBuffer();
        if (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
            matcher.appendTail(sb);
        }

        if (sb.length() < 1) {
            return underline;
        }
        return sb.toString();
    }


}
