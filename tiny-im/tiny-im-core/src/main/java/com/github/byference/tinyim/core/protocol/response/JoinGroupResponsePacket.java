package com.github.byference.tinyim.core.protocol.response;

import com.github.byference.tinyim.core.protocol.Command;
import com.github.byference.tinyim.core.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * JoinGroupResponsePacket
 *
 * @author byference
 * @since 2020-01-27
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class JoinGroupResponsePacket extends Packet {

    /**
     * 操作结果
     */
    private boolean success;

    /**
     * 操作明细
     */
    private String message;

    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_RESPONSE;
    }
}
