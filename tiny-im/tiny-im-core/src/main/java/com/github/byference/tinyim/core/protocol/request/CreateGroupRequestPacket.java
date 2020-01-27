package com.github.byference.tinyim.core.protocol.request;

import com.github.byference.tinyim.core.protocol.Command;
import com.github.byference.tinyim.core.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * CreateGroupRequestPacket
 *
 * @author byference
 * @since 2020-01-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CreateGroupRequestPacket extends Packet {

    /**
     * 群名称
     */
    private String groupName;


    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }
}
