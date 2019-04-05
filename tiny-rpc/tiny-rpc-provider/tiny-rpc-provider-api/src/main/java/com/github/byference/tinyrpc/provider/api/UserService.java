package com.github.byference.tinyrpc.provider.api;

import com.github.byference.tinyrpc.provider.entity.User;

/**
 * @author byference
 * @since 2019/03/31
 */
public interface UserService {

    /**
     * 通过用户id获取用户详情
     * @param id 用户id
     * @return {@link User}对象
     */
    User getUserById(String id);

}
