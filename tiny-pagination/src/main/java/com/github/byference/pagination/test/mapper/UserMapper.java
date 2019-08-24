package com.github.byference.pagination.test.mapper;

import com.github.byference.pagination.test.pojo.UserInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * UserMapper
 *
 * @author byference
 * @since 2019-08-24
 */
public interface UserMapper {


    /**
     * get all user info
     *
     * @return list of {@link UserInfo}
     */
    @Select("SELECT u.id 'id', u.user_name 'userName', u.user_level 'userLevel' FROM user_info u")
    List<UserInfo> getUserInfo();

}