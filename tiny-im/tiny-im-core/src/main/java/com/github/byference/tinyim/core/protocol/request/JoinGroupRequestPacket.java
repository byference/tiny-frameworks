package com.github.byference.tinyim.core.protocol.request;

import com.github.byference.tinyim.core.protocol.Command;
import com.github.byference.tinyim.core.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * JoinGroupRequestPacket
 *
 * @author byference
 * @since 2020-01-27
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class JoinGroupRequestPacket extends Packet {

    /**
     * group name
     */
    private String groupName;

    /**
     * user list
     */
    private List<String> userList;

    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_REQUEST;
    }
}
