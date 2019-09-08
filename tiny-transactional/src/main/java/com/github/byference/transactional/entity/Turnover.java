package com.github.byference.transactional.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 转账流水
 *
 * @author byference
 * @since 2019-09-08
 */
@Data
public class Turnover {

    /**
     * 用户id
     */
    private int id;

    /**
     * 转出方
     */
    private String transferor;

    /**
     * 转入方
     */
    private String transferee;

    /**
     * 交易金额
     */
    private BigDecimal amount;

    /**
     * 交易时间
     */
    private Date date;

}
