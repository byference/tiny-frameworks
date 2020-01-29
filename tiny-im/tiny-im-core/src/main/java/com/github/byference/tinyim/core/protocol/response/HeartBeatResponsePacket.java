package com.github.byference.tinyim.core.protocol.response;

import com.github.byference.tinyim.core.protocol.Command;
import com.github.byference.tinyim.core.protocol.Packet;

/**
 * HeartBeatResponsePacket
 *
 * @author byference
 * @since 2020-01-29
 */
public class HeartBeatResponsePacket extends Packet {

    @Override
    public Byte getCommand() {
        return Command.HEARTBEAT_RESPONSE;
    }
}
