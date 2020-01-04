package com.github.byference.tinyim.core.protocol.request;

import com.github.byference.tinyim.core.protocol.Command;
import com.github.byference.tinyim.core.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * MessageRequestPacket
 *
 * @author byference
 * @since 2020-01-03
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MessageRequestPacket extends Packet {

    /**
     * 单聊好友id
     */
    private String toUserId;

    /**
     * 单聊消息内容
     */
    private String message;


    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
