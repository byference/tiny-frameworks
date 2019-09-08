package com.github.byference.transactional.service;

import com.github.byference.transactional.entity.Turnover;

/**
 * Transactional {@link org.springframework.stereotype.Service}
 *
 * @author byference
 * @since 2019-09-08
 */
public interface TransactionalService {

    /**
     * 转账
     * @param turnover 转账行为信息
     */
    void transfer(Turnover turnover);
}
