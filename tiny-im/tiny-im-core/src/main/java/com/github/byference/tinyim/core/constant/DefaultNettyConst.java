package com.github.byference.tinyim.core.constant;

import io.netty.util.AttributeKey;

/**
 * DefaultNettyConst
 *
 * @author byference
 * @since 2019-12-29
 */
public interface DefaultNettyConst {

    String DEFAULT_HOST = "127.0.0.1";

    int DEFAULT_PORT = 8888;

    int MAGIC_NUMBER = 0xcafe;

    AttributeKey<String> TOKEN = AttributeKey.valueOf("Authorization");

    int HEARTBEAT_INTERVAL = 30;

    int READER_IDLE_TIME = 90;

}
