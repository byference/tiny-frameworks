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

    Byte LOGOUT_REQUEST = 5;

    Byte LOGOUT_RESPONSE = 6;

    Byte CREATE_GROUP_REQUEST = 7;

    Byte CREATE_GROUP_RESPONSE = 8;

    Byte JOIN_GROUP_REQUEST = 9;

    Byte JOIN_GROUP_RESPONSE = 10;

    Byte GROUP_NOTIFICATION_RESPONSE = 11;

    Byte GROUP_MESSAGE_REQUEST = 12;

    Byte GROUP_MESSAGE_RESPONSE = 13;

}