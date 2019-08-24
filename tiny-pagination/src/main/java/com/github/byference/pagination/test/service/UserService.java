package com.github.byference.pagination.test.service;

import com.github.byference.pagination.core.TinyPageHelper;
import com.github.byference.pagination.test.mapper.UserMapper;
import com.github.byference.pagination.test.pojo.UserInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User {@link Service}.
 *
 * @author byference
 * @since 2019-08-24
 */
@Slf4j
@AllArgsConstructor
@Service
public class UserService {

    private final UserMapper userMapper;

    /**
     * get all user info
     *
     * @return list of {@link UserInfo}
     */
    public List<UserInfo> getUserInfo(int pageNum, int pageSize) {

        TinyPageHelper.startPage(pageNum, pageSize);
        List<UserInfo> userInfo = userMapper.getUserInfo();

        log.info("query size: {}", userInfo.size());
        return userInfo;
    }

}