package com.github.byference.tinyim.core.protocol.response;

import com.github.byference.tinyim.core.protocol.Command;
import com.github.byference.tinyim.core.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * LoginResponsePacket
 *
 * @author byference
 * @since 2020-01-01
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginResponsePacket extends Packet {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 令牌
     */
    private String token;


    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }

}
