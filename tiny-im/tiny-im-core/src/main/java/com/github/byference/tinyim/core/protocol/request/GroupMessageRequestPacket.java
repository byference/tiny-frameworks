package com.github.byference.tinyim.core.protocol.request;

import com.github.byference.tinyim.core.protocol.Command;
import com.github.byference.tinyim.core.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * GroupMessageRequestPacket
 *
 * @author byference
 * @since 2020-01-27
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupMessageRequestPacket extends Packet {

    /**
     * group name
     */
    private String groupName;

    /**
     * group message
     */
    private String message;

    /**
     * message received time
     */
    private LocalDateTime messageReceivedTime;

    @Override
    public Byte getCommand() {
        return Command.GROUP_MESSAGE_REQUEST;
    }
}
