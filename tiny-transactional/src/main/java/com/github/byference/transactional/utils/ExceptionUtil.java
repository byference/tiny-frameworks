package com.github.byference.transactional.utils;

import java.util.Random;

/**
 * 模拟异常工具类
 *
 * @author byference
 * @since 2019-09-08
 */
public class ExceptionUtil {

    /**
     * 模拟异常
     */
    public static void error() {

        Random random = new Random();
        if (random.nextBoolean()) {
            throw new RuntimeException("网络中断");
        }
    }
}
