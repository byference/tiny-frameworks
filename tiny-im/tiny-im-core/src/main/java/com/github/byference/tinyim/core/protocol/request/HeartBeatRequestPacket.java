package com.github.byference.tinyim.core.protocol.request;

import com.github.byference.tinyim.core.protocol.Command;
import com.github.byference.tinyim.core.protocol.Packet;

/**
 * HeartBeatRequestPacket
 *
 * @author byference
 * @since 2020-01-29
 */
public class HeartBeatRequestPacket extends Packet {

    @Override
    public Byte getCommand() {
        return Command.HEARTBEAT_REQUEST;
    }
}
