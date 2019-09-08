package com.github.byference.transactional.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 账户
 *
 * @author byference
 * @since 2019-09-08
 */
@Data
public class Account {

    /**
     * 用户id
     */
    private int id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 待扣除金额
     */
    private BigDecimal account;

}
