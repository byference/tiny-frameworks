package com.github.byference.mybatis.test.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author byference
 * @since 2019/07/29
 */
@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 3958991476184945709L;

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户等级
     */
    private Integer userLevel;
}