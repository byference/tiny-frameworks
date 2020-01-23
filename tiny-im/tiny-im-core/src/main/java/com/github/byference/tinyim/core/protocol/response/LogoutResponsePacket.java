package com.github.byference.tinyim.core.protocol.response;

import com.github.byference.tinyim.core.protocol.Command;
import com.github.byference.tinyim.core.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * LogoutResponsePacket
 *
 * @author byference
 * @since 2020-01-23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LogoutResponsePacket extends Packet {

    /**
     * 注销结果
     */
    private boolean success;

    /**
     * 注销失败原因
     */
    private String message;

    @Override
    public Byte getCommand() {
        return Command.LOGOUT_RESPONSE;
    }
}
