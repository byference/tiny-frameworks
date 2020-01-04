package com.github.byference.tinyim.core.protocol;

/**
 * Command
 *
 * @author byference
 * @since 2019-12-31
 */
public interface Command {

    Byte LOGIN_REQUEST = 1;

    Byte LOGIN_RESPONSE = 2;

    Byte MESSAGE_REQUEST = 3;

    Byte MESSAGE_RESPONSE = 4;
}