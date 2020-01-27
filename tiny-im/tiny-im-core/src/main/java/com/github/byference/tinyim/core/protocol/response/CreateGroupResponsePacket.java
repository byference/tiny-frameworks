package com.github.byference.tinyim.core.protocol.response;

import com.github.byference.tinyim.core.protocol.Command;
import com.github.byference.tinyim.core.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * CreateGroupResponsePacket
 *
 * @author byference
 * @since 2020-01-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CreateGroupResponsePacket extends Packet {

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
        return Command.CREATE_GROUP_RESPONSE;
    }
}
