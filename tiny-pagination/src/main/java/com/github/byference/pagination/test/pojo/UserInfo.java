package com.github.byference.pagination.test.pojo;

import lombok.Data;

/**
 * UserInfo
 *
 * @author byference
 * @since 2019-08-24
 */
@Data
public class UserInfo {

    /**
     * user id
     */
    private Integer id;

    /**
     * username
     */
    private String userName;

    /**
     * user level
     */
    private String userLevel;

}