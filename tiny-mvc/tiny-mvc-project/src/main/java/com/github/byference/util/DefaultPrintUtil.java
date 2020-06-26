package com.github.byference.util;

/**
 * DefaultPrintUtil
 *
 * @author byference
 * @since 2020-06-25
 */
public class DefaultPrintUtil {

    private DefaultPrintUtil() {}


    public static void println(Object target) {
        System.out.printf(">>>>>>>>>>>>>>  [success] Thread Name: %s - %s \n", Thread.currentThread().getName(), target);
    }


    public static void printError(Object target) {
        System.err.printf(">>>>>>>>>>>>>>  [error] Thread Name: %s - %s \n", Thread.currentThread().getName(), target);
    }

}
