package com.github.byference.tinyrpc.core.util;

import java.util.UUID;

/**
 * Id Generator
 *
 * @author byference
 * @since 2019/04/06
 */
public class IdGenerator {

    /**
     * 生成不含{@code -}的UUID字符串
     */
    public static String generateUUID() {

        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
