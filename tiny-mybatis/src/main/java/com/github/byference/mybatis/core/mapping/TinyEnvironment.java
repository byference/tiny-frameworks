package com.github.byference.mybatis.core.mapping;

import lombok.Data;

/**
 * TinyEnvironment
 *
 * @author byference
 * @since 2019-08-03
 */
@Data
public class TinyEnvironment {

    private String driver;

    private String url;

    private String username;

    private String password;
}
