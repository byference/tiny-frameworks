package com.github.byference.tinyim.core.protocol;

import lombok.Data;

/**
 * Packet
 *
 * @author byference
 * @since 2019-12-31
 */
@Data
public abstract class Packet {

    /**
     * 协议版本
     */
    private Byte version = 1;

    public abstract Byte getCommand();

}
