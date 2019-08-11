package com.github.byference.mybatis.mapper;


import com.github.byference.mybatis.entity.UserInfo;

import java.util.List;

/**
 * @author byference
 * @since 2019/07/29
 */
public interface UserInfoMapper {


    /**
     * 根据ID获取 {@link UserInfo}
     *
     * @param id   用户id
     * @param name 用户名（测试多参数）
     * @return elements of {@link UserInfo}
     */
    UserInfo selectByIdAndName(Integer id, String name);


    /**
     * 获取全部 {@link UserInfo} 数据
     *
     * @return list of {@link UserInfo}
     */
    List<UserInfo> selectAll();


}