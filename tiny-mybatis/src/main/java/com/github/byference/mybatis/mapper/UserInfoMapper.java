package com.github.byference.mybatis.mapper;


import com.github.byference.mybatis.entity.UserInfo;

import java.util.List;

/**
 * @author byference
 * @since 2019/07/29
 */
public interface UserInfoMapper {

    /**
     * 获取全部 {@link UserInfo} 数据
     * @return elements of {@link UserInfo}
     */
    List<UserInfo> selectAll();


}