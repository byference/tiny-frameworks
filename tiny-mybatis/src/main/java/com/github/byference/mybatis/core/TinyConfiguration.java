package com.github.byference.mybatis.core;

import com.github.byference.mybatis.core.mapping.MapperStatement;
import com.github.byference.mybatis.core.mapping.TinyEnvironment;
import lombok.Data;

import java.util.Map;

/**
 * TinyConfiguration
 *
 * @author byference
 * @since 2019-08-03
 */
@Data
public class TinyConfiguration {


    private TinyEnvironment environment;

    private Map<String, MapperStatement> mapperStatementMap;

}
