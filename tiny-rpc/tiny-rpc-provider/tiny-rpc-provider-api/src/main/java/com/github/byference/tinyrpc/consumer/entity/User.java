package com.github.byference.tinyrpc.consumer.entity;

import lombok.Data;

/**
 * @author byference
 * @since 2019/03/31
 */
@Data
public class User {

    /**
     * 用户id
     */
    private String id;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户年龄
     */
    private int age;

}
