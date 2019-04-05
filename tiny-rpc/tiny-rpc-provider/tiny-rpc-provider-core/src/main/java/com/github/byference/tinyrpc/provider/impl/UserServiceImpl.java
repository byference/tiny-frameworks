package com.github.byference.tinyrpc.provider.impl;

import com.github.byference.tinyrpc.core.annotation.TinyService;
import com.github.byference.tinyrpc.provider.api.UserService;
import com.github.byference.tinyrpc.provider.entity.User;

/**
 * @author byference
 * @since 2019/03/31
 */
@TinyService("userService")
public class UserServiceImpl implements UserService {

    @Override
    public User getUserById(String id) {

        User user = new User();
        user.setId(id);
        user.setUsername("joy");
        return user;
    }

}
