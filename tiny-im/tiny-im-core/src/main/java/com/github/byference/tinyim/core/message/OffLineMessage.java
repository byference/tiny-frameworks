package com.github.byference.tinyim.core.message;

import com.github.byference.tinyim.core.constant.OffLineMessageSource;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * OffLineMessage
 *
 * @author byference
 * @since 2020-01-24
 */
@Data
public class OffLineMessage implements Serializable {

    private static final long serialVersionUID = 165928603707254455L;

    /**
     * 回复用户id
     */
    private String fromUserId;

    /**
     * group name
     */
    private String groupName;

    /**
     * 消息内容
     */
    private String message;

    /**
     * 消息回复时间
     */
    private LocalDateTime messageReceivedTime;

    /**
     * 消息来源
     */
    private OffLineMessageSource source;
}
