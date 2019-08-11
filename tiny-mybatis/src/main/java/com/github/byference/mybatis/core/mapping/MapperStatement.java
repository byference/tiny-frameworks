package com.github.byference.mybatis.core.mapping;

import lombok.Data;

/**
 * MapperStatement
 *
 * @author byference
 * @since 2019-08-05
 */
@Data
public class MapperStatement {

    private String id;

    private String namespace;

    private String parameterType;

    private String resultType;

    private String sql;

}
