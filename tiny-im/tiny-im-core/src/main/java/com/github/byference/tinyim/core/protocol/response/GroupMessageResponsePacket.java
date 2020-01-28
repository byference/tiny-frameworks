package com.github.byference.tinyim.core.protocol.response;

import com.github.byference.tinyim.core.protocol.Command;
import com.github.byference.tinyim.core.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * GroupMessageResponsePacket
 *
 * @author byference
 * @since 2020-01-27
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupMessageResponsePacket extends Packet {

    /**
     * group name
     */
    private String groupName;

    /**
     * message sender
     */
    private String sender;

    /**
     * message
     */
    private String message;

    /**
     * message received time
     */
    private LocalDateTime messageReceivedTime;

    @Override
    public Byte getCommand() {
        return Command.GROUP_MESSAGE_RESPONSE;
    }
}
