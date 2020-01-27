package com.github.byference.tinyim.core.protocol.response;

import com.github.byference.tinyim.core.protocol.Command;
import com.github.byference.tinyim.core.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * GroupNotificationResponsePacket
 *
 * @author byference
 * @since 2020-01-27
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupNotificationResponsePacket extends Packet {

    /**
     * group name
     */
    private String groupName;

    /**
     * notification
     */
    private String notification;

    @Override
    public Byte getCommand() {
        return Command.GROUP_NOTIFICATION_RESPONSE;
    }
}
