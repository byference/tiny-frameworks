package com.github.byference.tinyim.core.protocol.response;

import com.github.byference.tinyim.core.protocol.Command;
import com.github.byference.tinyim.core.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * MessageResponsePacket
 *
 * @author byference
 * @since 2020-01-03
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MessageResponsePacket extends Packet {

    /**
     * 回复用户id
     */
    private String fromUserId;

    /**
     * 回复用户名称
     */
    private String fromUserName;

    /**
     * 回复内容
     */
    private String message;


    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
