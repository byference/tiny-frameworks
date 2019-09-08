package com.github.byference.transactional.mapper;


import com.github.byference.transactional.entity.Account;
import org.apache.ibatis.annotations.Update;

/**
 * Transactional {@link org.apache.ibatis.annotations.Mapper}
 *
 * @author byference
 * @since 2019-09-08
 */
public interface TransactionalMapper {


    /**
     * 转出
     * @param account 用户实体{@link Account}
     * @return 更新记录条数
     */
    @Update("update account t set t.account = t.account - #{account} where t.user_name = #{userName}")
    int transferor(Account account);


    /**
     * 转入
     * @param account 用户实体{@link Account}
     * @return 更新记录条数
     */
    @Update("update account t set t.account = t.account + #{account} where t.user_name = #{userName}")
    int transferee(Account account);

}
