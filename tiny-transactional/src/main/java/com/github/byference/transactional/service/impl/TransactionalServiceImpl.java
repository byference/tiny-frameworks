package com.github.byference.transactional.service.impl;

import com.github.byference.transactional.annotation.TinyTransactional;
import com.github.byference.transactional.entity.Account;
import com.github.byference.transactional.entity.Turnover;
import com.github.byference.transactional.mapper.TransactionalMapper;
import com.github.byference.transactional.service.TransactionalService;
import com.github.byference.transactional.utils.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * default {@link TransactionalService} implementation
 *
 * @author byference
 * @since 2019-09-08
 */
@Service
public class TransactionalServiceImpl implements TransactionalService {


    @Autowired
    private TransactionalMapper transactionalMapper;


    @TinyTransactional
    @Override
    public void transfer(Turnover turnover) {

        // 1、假设金额都够，不做校验
        Assert.notNull(turnover, "转账接口参数不能为空");

        // 2、转出方扣除交易金额
        Account transferor = new Account();
        transferor.setUserName(turnover.getTransferor());
        transferor.setAccount(turnover.getAmount());
        transactionalMapper.transferor(transferor);

        // 3、模拟异常
        ExceptionUtil.error();

        // 4、转入方增加交易金额
        Account transferee = new Account();
        transferee.setUserName(turnover.getTransferee());
        transferee.setAccount(turnover.getAmount());
        transactionalMapper.transferee(transferee);
    }

}
